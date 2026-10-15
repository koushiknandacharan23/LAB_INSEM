# Dynamic API Gateway Routing with Eureka — Streaming App

A small Spring Boot multi-module Maven project for Spring Tool Suite (STS):
- `eureka-server` — service registry on port 8761
- `video-service` — video catalogue REST API on port 8081
- `api-gateway` — dynamic discovery-based routing on port 8080

## Requirements
- JDK 17
- Spring Tool Suite / Eclipse with Maven integration
- Internet access on first build so Maven can download dependencies

The project uses Spring Boot 3.3.5 and Spring Cloud 2023.0.3, a compatible release-train pairing.

## Import into STS
1. Extract this ZIP to a folder.
2. In STS, select **File → Import → Maven → Existing Maven Projects**.
3. Browse to the extracted `streaming-eureka-gateway` folder (the folder containing the root `pom.xml`).
4. Select the root project and click **Finish**. If prompted, right-click the project and choose **Maven → Update Project**.
5. Make sure the project uses JDK 17: **Project → Properties → Java Build Path**.

## Run order
Run each main class with **Run As → Spring Boot App**:
1. `EurekaServerApplication` in `eureka-server`
2. `VideoServiceApplication` in `video-service`
3. `ApiGatewayApplication` in `api-gateway`

Open the Eureka dashboard at http://localhost:8761 and check that `VIDEO-SERVICE` and `API-GATEWAY` register.

## Test
Direct video-service request (checks the service itself):
- http://localhost:8081/videos/all

Gateway request (checks Eureka discovery-based routing):
- http://localhost:8080/video-service/videos/all
- http://localhost:8080/video-service/videos/101

The response to `/videos/all` is a JSON list. The discovery locator uses the service ID in the URL and strips that first path segment before forwarding to the service. No video-service IP/port is hardcoded in the gateway configuration.

## Troubleshooting
- If Maven dependencies fail to download, check internet/proxy settings and run **Maven → Update Project**.
- Confirm ports 8761, 8081, and 8080 are free.
- Start Eureka before the two clients and wait a few seconds for registration.
- If you change Spring Boot or Spring Cloud versions, use their official compatibility matrix.
