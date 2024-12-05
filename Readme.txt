I assume that I am creating a global application.
I assume that the configurability of policies is about allowing value changes, rather than changing parameters within the policy itself.
I assume that one policy will be used per one request.I assume that only one policy can be applied at a time.
I assume that the request to calculate the price will include/require quantities, product IDs, and the policy ID.
I assume that the calculation endpoint will be hit when the shopping cart is being summed up —just before the payment step. This scope does not include the calculation of current prices displayed on the UI.