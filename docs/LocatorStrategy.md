**Strategy:**
1. Prefer dedicated test attributes (`data-testid`, `data-test`, `data-qa`) over production IDs/classes/XPath
2. Use stable IDs when available
3. Use `aria-*` only when it’s truly stable (accessibility labels can vary by locale/vendor)
4. Avoid class-based locators and long attribute chains unless semantic and stable
5. Use XPath as a last resort:
    - no indexes
    - no absolute paths

Locator picking order:

    data-test* -> stable id -> aria-* -> unique semantic attribute -> css -> xpath

**Exceptions / special cases (important):**

Non-product UI overlays (cookie consent, marketing popups, A/B banners) are **not part of the feature under test**.
- Locators for these can be less “clean”, but interactions must be **optional**.
- Never fail a test because a cookie banner changed or didn’t appear.
- Use `clickOptionalElement(...)` / best-effort handling.

XPath is allowed when:
- Element has no stable attributes
- Locator is relative to stable text or container

**Examples**

Bad (banner-specific, brittle class chain), use only if there is no other option:
> By.cssSelector(".fc-cta-consent.fc-primary-button")

Better (if truly stable, but still treat as optional):
> By.cssSelector("[aria-label='Consent']")

Never do:
> By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[2]/button[1]")

XPath example (last resort, still optional):
> By.xpath("//button[contains(@class,'fc-cta-consent')][contains(.,'Consent')]")