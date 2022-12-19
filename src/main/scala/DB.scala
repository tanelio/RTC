import java.sql.Timestamp

import akka.actor.ActorSystem
import org.slf4j.LoggerFactory
import slick.jdbc.H2Profile.api._
import slick.lifted.Tag

import scala.sys.process._

package Data {
  import slick.lifted.ProvenShape
  class DB {

    //  import ruler.Ruler

    import slick.jdbc.meta.MTable
    import rsyslog.Sensor

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

    // ToDo: implement actions & firewall chains
    class Actions(tag: Tag) extends Table[(String, String)](tag, "ACTIONS") {
      def id = column[String]("ID")

      def action = column[String]("ACTION")

      def * = (id, action)
    }

    class Code(tag: Tag) extends Table[(Int, String, String, Timestamp)](tag, "CODE") {
      def id = column[Int]("ID", O.PrimaryKey)
      def code = column[String]("CODE")
      def comment = column[String]("COMMENT")
      def added = column[Timestamp]("ADDED")
      def * = (id, code, comment, added)
    }
  }

}