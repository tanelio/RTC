import java.sql.Timestamp

import akka.actor.ActorSystem
import org.slf4j.LoggerFactory
import slick.jdbc.H2Profile.api._
import slick.lifted.Tag

import scala.sys.process._

package Data {
  import slick.lifted.{ProvenShape, TableQuery}

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.DurationInt
  object DB {

    //  import ruler.Ruler

    import slick.jdbc.meta.MTable
    import rsyslog.Sensor
    import scala.language.postfixOps

    import scala.concurrent.Await

    class Rules(tag: Tag) extends Table[(Int, String, String, Int, Int, Int, Boolean, String)](tag, "RULES") {
      def id = column[Int]("ID", O.PrimaryKey)

      def preamble = column[String]("PREAMBLE")

      def pattern = column[String]("PATTERN")

      def reps = column[Int]("REPS")

      def findtime = column[Int]("FINDTIME")

      def bantime = column[Int]("BANTIME")

      //def started = column[Timestamp]("STARTED")
      def active = column[Boolean]("ACTIVE")

      // tcp/udp
      // ignoreip
      // target matching
      def action = column[String]("ACTION")

      def * = (id, preamble, pattern, reps, findtime, bantime, active, action)
    }

    private val rules = TableQuery[Rules]


    // ToDo: implement actions & firewall chains
    class Actions(tag: Tag) extends Table[(String, String)](tag, "ACTIONS") {
      def id = column[String]("ID")

      def action = column[String]("ACTION")

      def * = (id, action)
    }

    private val actions = TableQuery[Actions]

    class Code(tag: Tag) extends Table[(Int, String, String, Timestamp)](tag, "CODE") {
      def id = column[Int]("ID", O.PrimaryKey)

      def code = column[String]("CODE")

      def comment = column[String]("COMMENT")

      def added = column[Timestamp]("ADDED")

      def * = (id, code, comment, added)
    }

    private val code = TableQuery[Code]

    //    val attacks = TableQuery[Attacks]
    //    val scans = TableQuery[Scans]
    //    val whois = TableQuery[Whois]

    val schema = /*attacks.schema ++ scans.schema ++ whois.schema ++*/ rules.schema ++ actions.schema ++ code.schema
    private val tables = List(/*attacks, scans, whois,*/ rules, actions, code)

    private val db = Database.forURL("jdbc:h2:~/scanner.h2;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    val sess = db.createSession()

    private val existing = db.run(MTable.getTables)
    private val f = existing.flatMap(v => {
      val names = v.map(mt => mt.name.name)
      val createIfNotExist = tables.filter(table =>
        (!names.contains(table.baseTableRow.tableName))).map(_.schema.create)
      db.run(DBIO.sequence(createIfNotExist))
    })
    Await.result(f, 30 seconds)

    if (Await.result(db.run(rules.length.result), 10 seconds) == 0) {
      println(s"Creating initial data")
      val setup = DBIO.seq(
        // todo update actions to match f2b (fail2ban) chains of iptables

        // Jul 17 21:21:19 srv2v sshd[11066]: Received disconnect from 116.31.116.37: 11:  [preauth]
        rules += (1, "", "^sshd.+Received disconnect from $ipv4: .+\\[preauth\\]", 1, 0, 3600, true, "ssh"),
        // Jul 17 21:30:10 srv2v dovecot: pop3-login: Disconnected (auth failed, 1 attempts): user=<device@dr-kalai.com>, method=PLAIN, rip=158.69.103.43, lip=162.206.51.1
        rules += (2, "", "^dovecot: pop3-login: Disconnected.+rip=$ipv4, lip=", 2, 10, 3600, true, "dovecot"),
        // Jul 30 11:39:07 srv2v saslauthd[1771]: do_auth         : auth failure: [user=miller] [service=smtp] [realm=otala.com] [mech=pam] [reason=PAM auth error]
        // Jul 17 21:41:07 srv2v sm-mta[11778]: v6I4f33V011778: mail.actus-ilw.co.uk [92.42.121.202] (may be forged) did not issue MAIL/EXPN/VRFY/ETRN during connection to MTA
        rules += (3, "^saslauthd.+do_auth.+auth failure.+\\[user=(\\w+)\\].+",
          "^sm-mta\\[.+\\[$ipv4\\].+did not issue MAIL/EXPN/VRFY/ETRN during connection to MTA", 1, 0, 3600, true, "sasl"),
        // Aug 12 12:14:26 srv5 sshd[8679]: Failed password for invalid user admin from 110.183.125.125 port 10875 ssh2
        // Aug 19 15:00:30 nuc sshd[25608]: Failed password for root from 218.65.30.122 port 42322 ssh2
        rules += (4, "", "sshd.+ Failed password .+from $ipv4 port.+", 1, 0, 3600, true, "ssh"),

        actions += ("ssh", "22"),
        actions += ("dovecot", "110,143"),
        actions += ("sasl", "25,465")
      )
      Await.result(db.run(setup), 30 seconds)
    }
    db.close()
  }
}