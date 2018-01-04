# Trustless Random Generator (TRG)
TRG does exactly what it's name describes. It is a random generator that requires no trust when using. It is used to remove third parties and keep transparency.

# Use cases
Imagine Bob is organizing a game jam (Game creation event following a specific theme). Note that Bob is also participating in the event. He says he will announce a "randomly generated" theme tonight at 8pm. How can you be certain that Bob won't just pick the theme of his choice 3 hours before the start of the event? TRG removes the "Organizer advantage" and the need of a third party to verify integrity. It ensures that the organizer randomly generates the data at the time he said he would.

# How does it work?
TRG uses Bitcoin's immutable blockchain as a solution. The program fetches the most recent block hash to use as a seed for the random generation. Users can verify that the used seed was a valid hash at the moment the organizer said he would generate the data. The blockchain used can be easily changed from the code, the Litecoin or Ethereum blockchains could be used for intervals, but at a greater risk of orphaned blocks.
