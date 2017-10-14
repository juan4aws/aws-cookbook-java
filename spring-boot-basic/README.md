# About




# Todo
* implement way to return invalid response from health check to 
see load balancer not send traffic to instance.

* publish custom metric

* see xray in action

* Read Replica load balancing, failover
* Maria DB Driver, Cross Region


# Findings/Learning
* ELB healthy host count reports different host count based on cross zone setting. 
When cross zone enabled it reports total instance count, otherwise avg per AZ.

* When ASG uses ELB health check it will auto scale based on this check otherwise
it scales on EC2 health checks which dont really drill in to running application.
