# Library App Analytics

図書館システム 分析サービス

図書館システムにおける貸出データを集計・分析するサービスです。  
Kafka から貸出イベントを消費し、週間・月間の人気書籍ランキングを  
フロントエンド向けに REST API として提供します。

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
