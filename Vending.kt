class Administration {
    private var drinks: MutableList<Drink>

    constructor(drinks: MutableList<Drink>){
    this.drinks = drinks }

    fun handleAdministration(drinks: MutableList<Drink>){
        var continueAdministration =  true

        while (continueAdministration){
        println("Administration panel:")
        println("1. Edit existing drinks")
        println("2. Delete existing drinks")
        println("3. Add new drinks")
        println("4. Update drinks list")
        println("5. Exit administration panel")
        println("Enter your choice: ")
        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> editExistingDrinks(drinks)
            2 -> deleteExistingDrinks(drinks)
            3 -> addNewDrinks(drinks)
            4 -> updateDrinksList()
            5 -> continueAdministration = false
            else -> println("Invalid choice.") }
        }
        println("Exiting administration panel.")
    }

    private fun editExistingDrinks(drinks: MutableList<Drink>) {
        println("Enter the index of the drink you want to edit: ")
        val index = readLine()?.toIntOrNull()

        if (index == null || index < 0 || index >= drinks.size) {
            println("Invalid index.")
            return
        }
    val selectedDrink = drinks[index]
        println("Editing drink:")
        println("Name: ${selectedDrink.name}")
        println("Price: ${selectedDrink.price}")
        println("Quantity: ${selectedDrink.quantity}")

        println("Enter new name: ")
        val newName = readLine()?.trim()?: selectedDrink.price

        println("Enter new price: ")
        val newPrice = readLine()?.toDoubleOrNull() ?: selectedDrink.price

        println("Enter new quantity: ")
        val newQuantity = readLine()?.toIntOrNull() ?: selectedDrink.quantity

        drinks[index] = Drink(newName.toString(), newPrice, newQuantity)
        println("Drink updated succesfully.")
    }

    private fun deleteExistingDrinks(drinks: MutableList<Drink>) {
        println("Enter the index of the drink you want to delete: ")
        val index = readLine()?.toIntOrNull()

        if (index == null || index < 0 || index >= drinks.size) {
            println("Invalid index.")
            return
        }

        drinks.removeAt(index)
        println("Drink deleted successfully.")
    }

    private fun addNewDrinks(drinks: MutableList<Drink>) {
        println("Enter the name of the new drink: ")
        val name = readLine()?.trim() ?: return

        println("Enter the price of the new drink: ")
        val price = readLine()?.toDoubleOrNull() ?: return

        println("Enter the quantity of the new drink: ")
        val quantity = readLine()?.toIntOrNull() ?: return

        drinks.add(Drink(name, price, quantity))
        println("Drink added successfully.")
    }

    private fun updateDrinksList() {
        println("Drinks list updated:")
        for (drink in drinks) {
            println("${drink.name}: ${drink.price} - ${drink.quantity}")
        }
    }
}
data class Drink(val name: String, val price: Double, var quantity: Int)

fun main() {
    val drinks = mutableListOf(
        Drink("Cola", 1.50, 10),
        Drink("Orange Juice", 1.75, 5),
        Drink("Water", 1.00, 15)
    )

    var continueShopping = true
    while (continueShopping) {
        // Display available drinks
        println("Available drinks:")
        // Display available drinks with selection numbers starting from 1
        for ((index, drink) in drinks.withIndex()) {
            if (drink.quantity > 0) {
                println("${index + 1}. ${drink.name}: ${drink.price}")
            }
        }

        // Prompt user to select an item directly using the displayed numbers
        println("Enter the selection number of the drink you want: ")
        val selection = readLine()?.toIntOrNull()
        if (selection == 808080){
            val administration = Administration(drinks)
            administration.handleAdministration(drinks)
            continue
        }
        if (selection == null || selection < 1 || selection > drinks.size) {
            println("Invalid selection.")
            continue
        }

        val selectedDrink = drinks[selection - 1]

        // Check if the item is available
        if (selectedDrink.quantity <= 0) {
            println("Item out of stock.")
            continue
        }

        // Prompt user for payment type
        println("Enter payment type (cash or card): ")
        val paymentType = readLine()?.trim()?.lowercase()
        if (paymentType != "cash" && paymentType != "card") {
            println("Invalid payment type.")
            continue
        }

        // Collect payment
        println("Enter payment amount: ")
        val paymentAmount = readLine()?.toDoubleOrNull()
        if (paymentAmount == null || paymentAmount <= 0 || paymentAmount < selectedDrink.price) {
            println("Invalid payment amount.")
            continue
        }

        // Dispense the item and update inventory
        selectedDrink.quantity -= 1
        println("${selectedDrink.name} dispensed.")

        // Calculate and return change if payment was in cash
        if (paymentType == "cash") {
            val change = paymentAmount - selectedDrink.price
            println("Change: ${"%.2f".format(change)}")
        }

        // Ask user if they want to continue shopping
        println("Continue shopping? (y/n): ")
        continueShopping = readLine()?.trim()?.lowercase() == "y"
    }

    println("Thank you for shopping!")
}
