# Test Plan for Eneco Web Testing and GoREST API Testing

## 1. Overview
This plan outlines testing for the integration of a third-party payment gateway on the Eneco website and the GoREST API. The goal is to ensure everything works smoothly, including accurate calculations for monthly bills and secure payment processing.

## 2. Objectives
- Test that the payment gateway works correctly and securely with the Eneco website.
- Verify the functionality of GoREST API endpoints.
- Ensure monthly bill calculations for electricity, gas, and both are accurate.
- Quickly catch any errors or issues that could affect users.

## 3. Scope
### In Scope
**Eneco Web Testing (using Playwright and TypeScript):**
- Payment gateway integration.
- Calculations for monthly electricity, gas, and combined bills.
- User interactions like login and form submission.
- Ensuring the site works on various browsers and devices.
- Checking that sensitive information is encrypted during payment.

**GoREST API Testing (using Rest Assured with Java):**
- Testing API functions like creating, reading, updating, and deleting users.
- Checking the response times of API calls.
- Validating data fields and ensuring correct status codes.
- Using a Bearer token for secured API actions.

## 4. Strategy
### Testing Types
- **Functional Testing**: Check if the features work as expected.
- **Integration Testing**: Ensure smooth communication between Eneco’s website and the payment gateway.
- **Security Testing**: Make sure user data is safe during transactions.
- **Performance Testing**: Check that the website and API respond quickly.
- **User Acceptance Testing**: Confirm that features meet user needs.

## 5. Test Case Examples
### Eneco Web Testing:
- Test if payment succeeds with valid card details.
- Validate monthly electricity bill calculations based on user input.
- Check combined electricity and gas bill calculations.
- Ensure error messages show up correctly for invalid inputs.

### GoREST API Testing:
- `GET /users`: Retrieve a list of users and verify details.
- `POST /users`: Create a new user and check the response.
- `DELETE /users/{id}`: Delete a user and confirm the user is removed.
- Validate API response times and ensure they meet standards.

## 6. Handling Changes
- If requirements change, update test cases to match the new needs.
- Focus on re-testing areas that are affected by the change.
- Keep the team updated with any changes and adjust priorities accordingly.

## 7. Test Automation
- **Eneco Web Testing**: Use Playwright and TypeScript for automating browser tests and integrate them with a CI tool like Jenkins.
- **GoREST API Testing**: Use Rest Assured with Java to automate API tests and run them as part of the build process in Jenkins.

## 8. Summary
This test plan ensures that the new payment integration on Eneco's website is accurate and user-friendly, and that the GoREST API functions correctly. By using automation tools like Playwright and Rest Assured, testing is efficient and effective.
