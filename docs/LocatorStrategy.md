**Strategy:**
1. Prefer custom data-* attributes dedicated for testing over production IDs, classes, and XPath
2. Use _aria-*_ when _data-*_ is not available 
3. Avoid class-based locators and long attribute chains unless semantic and stable
4. Use XPath as a last resort 
   - no indexes
   - no absolute paths

Locator type picking order:

    data-* (test-only) -> stable id -> aria-* -> unique semantic attribute -> css -> xpath

**Exceptions:**

XPath is allowed when:
- Element has no stable attributes
- Locator is relative to stable text or class


**Examples**

Bad:
>> protected static final By COOKIES_ACCEPT_BUTTON = By.cssSelector(".fc-cta-consent.fc-primary-button")

Good:
> protected static final By COOKIES_ACCEPT_BUTTON = By.cssSelector("[aria-label='Consent']");
---
>
Bad:
>protected static final By COOKIES_ACCEPT_BUTTON = By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[2]/button[1]")


Good:

// for example if 'fc-cta-consent' class is added to multiple buttons, and button can be
located only via inner element text. 

- avoid exact attribute match (ex. /p[someCondition]/..)
- prefer the filtering over reaching directly inside the path
>protected static final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[contains(@class, 'fc-cta-consent')][.//text()[contains(., 'Consent')]]")