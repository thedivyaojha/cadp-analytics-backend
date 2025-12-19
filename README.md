# CADP – Creator Analytics & Distribution Portal (Backend)

A modular, ingestion-focused backend system for aggregating, normalizing, and analyzing music revenue data across multiple digital platforms (DSPs, Telco Operators, YouTube).

This service acts as the **financial and analytics backbone** between a music distributor (Admin) and content creators (Artists), ensuring accurate monthly revenue reporting in INR with strong data integrity guarantees.

---

## 🎯 Core Objectives

- Ingest heterogeneous revenue reports from multiple platforms
- Normalize currencies into INR at ingestion time
- Aggregate metrics deterministically by **ISRC + Platform + Month**
- Persist analytics in a query-optimized relational model
- Provide a clean foundation for dashboards, exports, and creator-facing views

---

## 🧱 Architecture Overview

**Architecture Style:** Modular Monolith  
**Design Principle:** Clear separation of ingestion, domain, persistence, and infrastructure layers

### High-Level Flow

File Upload (CSV / XLSX)
↓
Ingestion Service
↓
Normalization & Aggregation
↓
FX Conversion (USD → INR)
↓
Persistence (PostgreSQL)


This flow represents **every ingestion pipeline** in the system.

---

### Then add this 👇

```markdown
### Module Responsibilities

- **ingestion**
  - Platform-specific parsers (DSP, Operator, YouTube)
  - No business logic leakage outside ingestion

- **domain**
  - Core entities (Song, User)
  - Status lifecycle and ownership rules

- **service**
  - Cross-cutting business logic
  - Currency conversion and shared computation

- **repository**
  - Data access via Spring Data JPA
  - Database constraints enforce correctness

- **util**
  - Converters, normalizers, and infrastructure helpers



