# Library App Analytics Service / 分析サービス

Distributed Library System — Analytics Service  

分散型図書館システム — 分析サービス

---

Overview / 概要

The Analytics Service is a hybrid event-driven and query-facing microservice within the distributed library platform.

It is responsible for consuming borrowing events through Kafka, aggregating analytical metrics, and exposing ranked
chapter insights to client-facing applications through RESTful APIs.

The service combines asynchronous event processing with synchronous analytical querying, enabling real-time usage
statistics and ranking retrieval.

---

Analytics Service は分散型図書館システムにおけるハイブリッド型イベント駆動分析サービスです。

Kafka 経由で貸出イベントを非同期に受信し、分析メトリクスを集計した上で、ランキング情報を REST API 経由で提供します。

非同期イベント処理と同期的な分析クエリ提供を組み合わせた Spring Boot マイクロサービスとして設計されています。

---

Service Boundaries / サービス境界

Provides

- Borrowing event consumption
- Analytical metric aggregation
- Weekly chapter ranking generation
- Monthly chapter ranking generation
- Paginated analytical queries
- REST-based analytical exposure

Does Not Handle

- Catalog metadata management
- Borrow transaction execution
- User management
- Authentication / authorization
- Inventory consistency

---

## Badges

<!-- Code Quality & Tests -->
[![Tests](https://github.com/damouu/library-app-analytics/actions/workflows/run-tests.yml/badge.svg?branch=test)](https://github.com/damouu/library-app-analytics/actions/workflows/run-tests.yml)
[![Merge PR](https://github.com/damouu/library-app-analytics/actions/workflows/merge-pr.yml/badge.svg)](https://github.com/damouu/library-app-analytics/actions/workflows/merge-pr.yml)
[![Prepare](https://github.com/damouu/library-app-analytics/actions/workflows/prepare.yml/badge.svg)](https://github.com/damouu/library-app-analytics/actions/workflows/prepare.yml)
[![YouTrack-Staging](https://github.com/damouu/library-app-analytics/actions/workflows/youtrack-staging.yml/badge.svg)](https://github.com/damouu/library-app-analytics/actions/workflows/youtrack-staging.yml)
[![YouTrack Closed](https://github.com/damouu/library-app-analytics/actions/workflows/youtrack-done.yml/badge.svg)](https://github.com/damouu/library-app-analytics/actions/workflows/youtrack-done.yml)

<!-- Coverage -->
[![Codecov](https://codecov.io/gh/damouu/library-app-analytics/branch/test/graph/badge.svg)](https://codecov.io/gh/damouu/library-app-analytics)

<!-- Docker -->
[![Docker Build](https://github.com/damouu/library-app-analytics/actions/workflows/build-and-publish.yml/badge.svg)](https://github.com/damouu/library-app-analytics/actions/workflows/build-and-publish.yml)
[![Docker Image](https://img.shields.io/docker/v/damou/library-app-analytics?label=docker&logo=docker)](https://hub.docker.com/r/damou/library-app-analytics)
[![Docker Pulls](https://img.shields.io/docker/pulls/damou/library-app-analytics?logo=docker)](https://hub.docker.com/r/damou/library-app-analytics)

<!-- Git / Version -->
[![Git Tag](https://img.shields.io/github/v/tag/damouu/library-app-analytics?logo=github)](https://github.com/damouu/library-app-analytics/tags)

<!-- Observability / Monitoring -->
![OpenTelemetry](https://img.shields.io/badge/OpenTelemetry-instrumented-brightgreen)
![Kafka](https://img.shields.io/badge/Kafka-integrated-orange)
![Prometheus](https://img.shields.io/badge/Prometheus-monitored-blue)

---

Responsibilities / 責務

English

- Consume borrowing events
- Aggregate usage metrics
- Generate ranking datasets
- Expose analytical resources
- Support filtered analytical retrieval
- Maintain analytical consistency

日本語

- 貸出イベント処理
- 利用分析集計
- ランキング生成
- 分析データ提供
- フィルタリング対応
- 分析整合性維持

---

Technology Stack / 技術スタック

Category| Technology
Runtime| Java 21
Framework| Spring Boot 2.7
Messaging| Kafka
Persistence| Spring Data JPA
Database| PostgreSQL / H2
API Documentation| Springdoc OpenAPI
Validation| Bean Validation
Testing| JUnit 5 / Mockito / JaCoCo / Instancio
Containerization| Docker
CI/CD| GitHub Actions

---

API Endpoints / API エンドポイント

Analytics

GET /public/top-chapters

Returns paginated ranked chapter analytics.

---

Query Parameters

period

Supported values:

- currentweek 
- lastweek
- lastmonth

---

Example

GET /public/top-chapters?period=currentweek&page=0&size=10

---

Query Features / クエリ機能

Supported capabilities:

- Pagination
- Sorting
- Period-based filtering

---

Event Processing / イベント処理

Consumes Kafka borrowing events.

Processes:

- Borrow creation events
- Ranking recalculation
- Analytical metric aggregation

---

API Documentation / API ドキュメント

/swagger-ui/

---

Local Development / ローカル開発

Requirements

- Java 21
- Maven
- Docker
- PostgreSQL
- Kafka

---

Run

docker compose up --build

---

Testing / テスト

./mvnw verify

Includes:

- Unit tests
- Slice tests
- Integration tests
- Coverage verification

---

Configuration / 設定

SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_KAFKA_BOOTSTRAP_SERVERS=

Environment-driven configuration.

---

Monitoring / モニタリング

/actuator/health

/actuator/prometheus

---

Analytical Pipeline / 分析パイプライン

Borrow Event  
↓  
Kafka Consumer  
↓  
Aggregation Layer  
↓  
Database Persistence  
↓  
Analytics REST API

---

Build Quality / 品質保証

The build pipeline enforces:

- Automated test execution
- Coverage thresholds
- Pull request validation
- Docker image publication
- Static quality checks

---

License / ライセンス

MIT
