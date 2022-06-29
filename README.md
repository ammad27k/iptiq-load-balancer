

# Iptiq-custom-loadbalancer
Custom loadbalancer with strategies of random and roundrobin scheduling

Objective(Task description):
----------------------------
Step 1 – Generate provider
  Generate a Provider that, once invoked on his get() method, retrieve an unique identifier (string) of the provider instance
  
Step 2 – Register a list of providers
  Register a list of provider instances to the Load Balancer - the maximum number of providers accepted from the load balancer is 10
  
Step 3 – Random invocation
  Develop an algorithm that, when invoking multiple times the Load Balancer on its get() method, should cause the random invocation of the get() method of any    r   registered provider instance.
  
Step 4 – Round Robin invocation
  Develop an algorithm that, when invoking multiple times the Load Balancer on its get() method, should cause the round-robin (sequential) invocation of the get()     method of the registered providers.
  
Step 5 – Manual node exclusion / inclusion
  Develop the possibility to exclude / include a specific provider into the balancer

Step 6 – Heart beat checker
  The load balancer should invoke every X seconds each of its registered providers on a special method called check() to discover if they are alive – if not, it       should exclude the provider node from load balancing.

Step 7 – Improving Heart beat checker
  If a node has been previously excluded from the balancing it should be re-included if it has successfully been “heartbeat checked” for 2 consecutive times

Step 8 – Cluster Capacity Limit
  Assuming that each provider can handle a maximum number of Y parallel requests, the Balancer should not accept any further request when it has (Y*aliveproviders)   incoming requests running simultaneously


# Installation and Applicaiton Run
# Config
If you want to change the loan balancer machanism then you go to file "load-balancer-config.yml" and change "loadBalancerType" to "ROUND_ROBIN" or "RANDOM"
Also you can change the provider configuration as well. 

# With Docker
Step 1 - Go to root directory of the applicaiton

Step 2 -  run -> docker build -t loan-balancer:version .

Step 3 -  run -> docker run -it loan-balancer:version

# Without Docker
Step 1 - Go to root directory of the applicaiton

Step 2 - run -> mvn clean package

Step 3 - run -> java -jar load-balancer-1.0-SNAPSHOT-jar-with-dependencies.jar


# OutPut 

10:30:41.696 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 4dc8a835-493a-41a8-9f1c-a70452909373
10:30:42.697 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 88cb4a03-92d1-43fe-b6ef-f08eef0f4800
10:30:43.697 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 4d3a0b06-96a7-4eb4-b858-48258abfdd26
10:30:44.698 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 27fb6cc3-105b-4980-a3c6-bd97560ee4f0
10:30:45.698 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 45802ccc-58fd-454e-a8eb-8e1a787665f1
10:30:46.699 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 6016316d-7612-46ea-a834-dc8fd93bcfe7
10:30:47.700 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> f5550b2d-57ec-4a70-a89e-eeaa436aa21c
10:30:48.701 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 63118d14-07cd-4382-859c-69a534a24641
10:30:49.702 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> dc1d61d2-6c54-4bc7-aeba-f12bc9d06e7e
10:30:50.702 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 07f77394-5af5-4c75-b87b-d247d1b45579
10:30:51.704 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 6e096cea-b363-4b4c-8d51-59eab35c1263
10:30:52.705 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 44e43c63-ed11-4741-b190-7546a8953437
10:30:53.706 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 73487b1e-21ed-45ba-a371-f2240f319712
10:30:54.707 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 954add08-ad73-467d-a03d-8d3d20fed5cd
10:30:55.707 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> eb7d25fa-d4c3-4ec8-b550-7c227bc41032
10:30:56.708 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> f1941961-1dee-484f-ada8-025155daeb08
10:30:57.708 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> da4608af-b49b-4ca2-bea7-38ac341481e5
10:30:58.709 [ForkJoinPool.commonPool-worker-7] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> d1b8ea71-07b9-4a76-a55a-ac3f57d20608
10:30:59.709 [ForkJoinPool.commonPool-worker-3] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 1, Identifier -> 370aae5d-cc12-4a2f-9977-6d5044a5912f
10:31:00.710 [ForkJoinPool.commonPool-worker-5] DEBUG com.swiss.LoadBalancerApplication : Name -> Provider 2, Identifier -> 3841cdc7-0dd1-427c-8a15-77711cd5a716
