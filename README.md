# Rexoubapp

**Rexoubapp** is a multi-user, easy-to-use monitoring platform build to monitor physical  and  virtual  servers  based  on  Debian  GNU/Linux.  
> Monitoring  is  to collect and supervise  a set  of  system  metrics and check  the  status  of  some services. 

**Rexoubapp** is basically a playground to explore and play with Spring Framework with Spring Boot.

**Rexoubapp** allows to indicate critical usage thresholds in metrics or changes in  service  status  that  must  be  logged.  In addition  to  this,  it  allows  to  add contacts that will be notified, by email or Slack messages, when such events occur.

The platform is composed of two elements:
- A Spring Framework based backend with Spring Boot consisting of:
  - The REST API which allows you to use the platform using HTTP.
  - A worker responsible to harvest and process servers’ data.
  - An asynchronous message bus used for recording monitoring events and to notify them.

-  A  simple  JavaScript  dashboard  build  with  React  and  Redux.  [**Rexoubapp dashboard**](https://github.com/edusalguero/rexoubapp-dashboard) consumes the API and show most relevant account's information.
