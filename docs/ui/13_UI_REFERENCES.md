# UI REFERENCES — DIRECTION FOR CODEX

## Purpose
This file defines the UI direction for the app.
It is not a collection of random inspiration.
Each reference must explain:
- what to copy;
- what to avoid;
- why it matters.

## Global UI target
The desired UI is:
- dark mode first;
- dense but readable;
- finance-oriented;
- sober, not flashy;
- modular;
- practical before decorative.

The product should feel:
- trustworthy;
- local;
- fast;
- information-first.

## Important rule
UI work must reflect the real source of truth.
Do not build a beautiful fake dashboard disconnected from portfolio logic.

## Layout principles
- prioritize information hierarchy over visual effects;
- avoid giant empty areas;
- avoid oversized headers;
- keep key metrics visible quickly;
- prefer cards only when they improve readability;
- tables are acceptable and often preferable for finance data.

## Portfolio screen direction
Wanted:
- compact summary strip at top;
- portfolio table as main block;
- readable columns;
- clear positive/negative PnL styling;
- optional right-side detail pane later.

Avoid:
- oversized hero sections;
- giant decorative charts above the actual portfolio;
- mobile-first spacing that wastes desktop space;
- excessive gradients or glow effects.

## Asset page direction
Wanted:
- clear asset header;
- price and variation visible immediately;
- main chart area;
- key metrics block;
- current held position block if relevant;
- concise notes / events section.

Avoid:
- overloading the page with dozens of secondary widgets;
- fake analyst sentiment panels without real data;
- duplicated metrics shown in multiple places.

## Journal / operations screen direction
Wanted:
- chronological table;
- filter bar;
- fast scan of asset, side, quantity, price, fee;
- minimal friction for manual data audit.

Avoid:
- card-based journal layouts for long operation history;
- decorative timeline UI that hides data density.

## Component guidance
Prefer:
- tables for holdings and journal;
- restrained metric cards;
- simple sidebar or tab navigation;
- consistent typography scale;
- strong spacing discipline;
- high contrast for key numeric information.

Avoid:
- animation-heavy UI;
- glassmorphism;
- neon trading UI clichés;
- unnecessary rounded giant containers everywhere.

## Visual references to add later
When adding PNGs or links, use this format:

### Reference X — [name]
Type:
- screenshot / PNG / URL

Use for:
- portfolio density / asset page / journal / navigation / metric cards

Copy:
- exact points to reproduce

Avoid:
- exact points not to reproduce

Reason:
- why this reference is relevant

## Recommended repository assets
Create a folder such as:
- `docs/ui_refs/`

Suggested contents:
- annotated screenshots;
- rough wireframes;
- desktop layout sketches;
- examples of desired table density;
- examples of chart placement.

## Annotation rule
A UI reference is only valid if annotated.
Do not dump raw inspiration without comments.

## Current UI decision
Until richer references are added, Codex should default to:
- dark desktop finance UI;
- compact layout;
- table-first portfolio;
- chart-secondary asset page;
- journal-first operation audit experience.
