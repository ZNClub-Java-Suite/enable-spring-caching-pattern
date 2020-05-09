# enable-spring-caching-pattern
Design a Caching Pattern to support multiple Caching implementations

## Proposed designs

- Simple Key Value implementation
- VIEW-GET design

## Design descriptions

### Simple Key Value implementation

- Scenario 1

1. Request R1 received for Input I1
2. Service checks cache
	- Cache HIT then respond R1 with ouput O1
	- Cache MISS then
		1. Forward request to responsible Service S1
		2. Retrive output O1 from S1 and store in cache
		3. Respond R1 with output O1
3. Request R2 received for Input I1
4. Cache hit occurs

- Scenario 2

1. Request R1 received for Input I1
2. Service checks cache
	- Cache HIT then respond R1 with ouput O1
	- Cache MISS then
		1. Forward request to responsible Service S1
		2. Request R2 received for Input I1
		3. <TO-DO>
		4. Retrive output O1 from S1 and store in cache
		5. Respond R1 with output O1
		
3. Request R3 received for Input I1
4. Cache hit occurs


### VIEW-GET design

- Scenario 1 for VIEW: request from cache only, client is aware this data might be stale

1. Request R1 received for Input I1
2. REQUEST HANDLER forwards to Router R1
3. Router R1 manages routing for VIEW vs GET request
4. R1 forwards to GET CACHE
5. GET CACHE performs "get from cache" and forwards ouput O1 to R2
6. Router R2 manages routing for cache HIT vs cache MISS
	- Cache HIT then
		1. forward O1 to RESPONSE HANDLER
		2. get cache details OC1 like how long O1 was cached
		3. Respond R1 with O1 and OC1
	- Cache MISS then split messages as M1 and M2
		- For M1
			1. forward empty O1 to RESPONSE HANDLER
			2. add message displaying that fetching details from source
			3. Respond R1 with O1 and OC1
		- For M2
			1. forward R1 to PUT Cache
			2. PUT cache executes R1 and puts ouput O2 in cache
			3. forward to GET Cache and continue from step 5
			
- Scenario 1 for GET: request from source only, client is aware this is time consuming

1. Request R1 received for Input I1
2. REQUEST HANDLER forwards to Router R1
3. Router R1 manages routing for VIEW vs GET request
4. R1 forwards to EVICT CACHE
5. EVICT CACHE removes I1 entry in cache
6. Coverts GET to VIEW and forwards to Router R1
























