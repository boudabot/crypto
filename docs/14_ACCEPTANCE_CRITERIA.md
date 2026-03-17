# ACCEPTANCE CRITERIA — DEFAULT CHECKLIST

## Purpose
This file defines when a task is considered truly finished.
Codex should use it to avoid stopping at “code written but not verified”.

## Global completion rule
A task is not complete if:
- the app no longer runs;
- compile status is unknown;
- the user cannot understand what changed;
- the new feature has no minimum verification path.

## Every meaningful DEV_APP response must include
- short audit;
- plan if the task is complex;
- files modified;
- commands run;
- compile/test result;
- key tradeoffs or arbitrations.

## Mandatory technical checks
### Build
- Maven build status must be reported.
- If build is not run, the reason must be stated clearly.

### Runnable state
- The current Java application must remain runnable unless the task explicitly changes the run path.
- If not verified, say so explicitly.

### Scope control
- Changes must stay within the requested scope.
- No hidden refactor of unrelated modules unless justified.

### Documentation
If the change affects behavior, structure or workflow, update the minimum relevant docs:
- `README.md`
- `AGENTS.md`
- affected file in `docs/`

## Feature-level acceptance criteria

### For domain model work
Done when:
- fields are explicit;
- model responsibilities are clear;
- no duplicate contradictory model exists without reason;
- basic test scenario or validation path is documented.

### For portfolio calculations
Done when:
- buy with fee works;
- sell with fee works;
- partial sell works;
- weighted average cost remains coherent;
- realized and unrealized PnL are exposed;
- edge cases or assumptions are stated.

### For manual entry
Done when:
- invalid input is rejected or surfaced clearly;
- a buy can be recorded;
- a sell can be recorded;
- persisted or in-memory effect is visible in portfolio output.

### For portfolio UI
Done when:
- holdings are visible in a readable structure;
- average cost, current value and PnL are visible;
- data is real or explicitly marked as mock;
- no misleading placeholder is presented as truth.

### For journal UI
Done when:
- operations are readable chronologically;
- fee visibility is preserved;
- buy/sell distinction is obvious;
- the journal matches the source model.

### For import work
Done when:
- a sample file can be processed;
- unsupported rows are surfaced clearly;
- duplicate handling behavior is explicit;
- import does not silently corrupt the ledger.

## Preferred command reporting format
Example:
- `mvn -q -DskipTests compile`
- `mvn test`

If commands differ, report the exact command actually used.

## Preferred result reporting format
- Build: OK / FAIL / NOT RUN
- Tests: OK / FAIL / NOT RUN
- App run check: OK / FAIL / NOT VERIFIED

## Red flags that must be stated explicitly
- compile not run;
- tests missing;
- mock data still used;
- calculations assumed but not verified;
- migration path incomplete;
- current Java app not checked after change.

## Definition of a good Codex iteration
A good iteration:
- improves the repo without drama;
- preserves the current app;
- moves one meaningful step forward;
- leaves the project clearer than before.
