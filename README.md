# RTC
Real-Time Compiler; SCADA & IoT management program
- soft-programmable (programmable while running) - programming changes carry out via database
- continuously running, rules saved real time to database
- initial database is H2 (eeasy to change to MariaDB/MySQL or any of te 11 databases supported by Slick)
- When attaching to a SCADA/IoT source, it will measure the frequency data is received, and alert when data does not arrive timely
- receiving data is stored into a sparse spreadsheet type representation, where t can be used like spreadsheet using the internal language
- responses are real-time, for example iptables filters or SCADA style supervisory controls
- GraphQL to support various JS presentations (or web) for graphic visualization; the visualization will work on any web based device, instead of requiring a X based HMI
- This project has just started, but it shares a lot of code with Scanner project (same repo) -- this is not my first time in rodeo
- The IoT component reads rsyslog (including markers)

Components used:
- Scala 2.13.10, with Akka 2.7 and Slick 3.4.1
- Reflection (self programming/compiling)
- Possible add-ons: Camel, Rhino & JProlog

Not decided:
- Should RTC clean-up iptables on startup?
- RTC should conform to existing iptables (denyhosts & Fail2Ban)

Firewall rules:
- Trying to co-exist with pre-existing firewalls
- Trying to not deteriorate performance, despite potentially blocking half the internet (~2 billion IPs)
- Flush firewalls, esp (INPUT)
- Allow for pre-amble (policy, internal, DMZ, pre-established)
- Allow for "all ports" CHAIN
- Different port CHAINs (e.g. imap|pop3|smtp, web, VoIP)
- 