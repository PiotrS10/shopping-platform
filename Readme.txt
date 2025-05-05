I assume that:
- I am building a global application.
- configurability of policies is about allowing value changes, rather than changing parameters within the policy itself.
- one policy will be used for one request.
- request to calculate the price will include quantities, product IDs, and the policy ID.
- only one policy can be applied at a time.
- calculation endpoint will be hit when the shopping cart is being summed up —just before the payment step. This scope does not include the calculation of current prices displayed on the UI.
- policy is triggered when a certain quantity of the same product is reached, and the discount is applied only to that product multiplied by its quantity in the cart in the case of a percentage-based policy. In the case of quantity-based policies, the price is calculated as a value that reduces the amount in the cart.
- each product and the quantity of its order undergoes a separate verification process to determine if the given policy can be applied in the endpoint.
In the domain, I am creating ValueObjects to encapsulate domain logic (i.e., the actual business representation). Therefore:
- I have created a class called Amount, which checks (upon creation) whether the provided 'value' fits within the limits defined by the business (and the logic).
	This means there’s a lower limit (logically, because the value cannot be less than 1), and an upper limit—this upper value is provided by the business.
I assume that all values in the request are primitive types, and only in the value objects do I validate the correctness of these objects.
Since the application is part of a shipping platform, I assume that I separate the logic and encapsulate the calculation process in one application.
I assume that this application will be used in different contexts (e.g., by a salesperson, a store, a customer).
It is important for us to stay as close to the business as possible and to define our business domain according to DDD (Ports and Adapters)—for high scalability, testability, and adherence to principles.
I am aware that I could use UUID as the type for request parameters, but this would create inconsistency since some of the logic would be validated at the request level, while other logic would be validated at the value object level.
I assumed that the request could send a list of products, or more specifically, their UUIDs.
If there are problems with deserialization of even one UUID, the entire request/cart will be rejected.
Due to the fact that the application will need to be highly efficient (as price calculation is a step in many business processes), I assume that:
The application's deployment architecture will account for high traffic, including resource reservations in the cluster and scalability options.
Logging, metrics, and alerting will be implemented using appropriate tools such as Grafana, PagerDuty, and ElasticSearch.

Sample payload for sending a request to http://localhost:8080/api/calculate
{
    "productLines": [
      {
        "productId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
        "amount": 10
      },
      {
        "productId": "e6342d0e-5457-4977-a6de-4c7f74ef9f2d",
        "amount": 5
      },
            {
        "productId": "7e3c6ff1-12ae-4cb2-98e0-d42a4f60617f",
        "amount": 9
      }
    ],
  "policyId": "f47ac10b-58cc-4372-a567-0e02b2c3d471"
}
