## EXECUTION HARDENING

### Reading discipline
Codex must not read the whole documentation tree by default.
It must read only the minimum useful set for the task.

#### DEV_APP hot path
Read in priority:
1. `docs/00_CONTEXT__CURRENT_REPO.md`
2. `docs/10_PRODUCT_TARGET__CURRENT.md`
3. `docs/11_ROADMAP__NEXT_STEPS.md`
4. `docs/12_FRONTEND_BACKEND_CONTRACT.md`
5. `docs/14_ACCEPTANCE_CRITERIA.md`

#### MARKET / PORTFOLIO hot path
Read in priority:
1. `docs/01_TRADING_RULES__CORE.md`
2. `docs/02_PORTFOLIO__SOURCE_OF_TRUTH.md`
3. `docs/03_WATCHLIST__ACTIVE.md`
4. `docs/04_EXECUTION__BROKER_CONSTRAINTS.md`
5. `docs/05_MARKET_STATE__LATEST.md`

### Documentation status policy
Each important doc should be treated as one of:
- ACTIVE = current source of truth
- REFERENCE = useful secondary context
- LEGACY = obsolete or replaced, do not use by default

Rules:
- never use a LEGACY file as primary decision input;
- if two files overlap, one must become the clear primary source;
- replaced files should be marked or moved, not silently left ambiguous;
- avoid duplicate active docs on the same topic.

### Minimal-diff rule
Prefer the smallest safe change that reaches the goal.
Do not refactor broadly unless explicitly requested.

### Validation gate
For every non-trivial task, Codex must perform the minimum relevant validation.

Examples:
- docs-only change: path/link/coherence check
- Java change: Maven compile or nearest safe validation
- frontend change: nearest relevant build/lint/test if applicable

If validation is not run, Codex must say so explicitly.

### Response format
By default, respond with:
1. brief audit
2. brief action plan if needed
3. files changed
4. commands run
5. validation result
6. blockers or assumptions

### Obsolete file discipline
If Codex finds stale, duplicate, or obsolete files, it must:
- list them clearly;
- propose ACTIVE / REFERENCE / LEGACY classification;
- avoid deleting them without explicit approval.