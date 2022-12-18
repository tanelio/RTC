# RTC
Real-Time Compiler; SCADA & IoT management program
- soft-programmable (programmable while running) - programming changes carry out via database
- continuously running, rules saved real time to database
- initial database is H2 (eeasy to change to MariaDB/MySQL or any of te 11 databases supported by Slick)
- When attaching to a SCADA/IoT source, it will measure the frequency data is received, and alert when data does not arrive timely
- receiving data is stored into a sparse spreadsheet type representation, where t can be used like spreadsheet using the internal language
- responses are real-time, for example iptables filters or SCADA style supervisory controls
- GraphQL to support carious JS presentations (or web) for graphic visualization
