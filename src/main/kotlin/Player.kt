class Player : IPlayerAndNPC  {

    // default settings
    override var name: String = "You"
    override var isAlive: Boolean = true
    override var buffStatus: String = ""
    override var debuffStatus: String = ""
    override var insertedBullet: Int = 0
    override var onFire: Int = 0
    override var times: Int = 1

    private var buffsInventory: MutableList<String> = mutableListOf()
    private var debuffsInventory: MutableList<String> = mutableListOf()
    var coin: Int = 10

    fun shop() {
        Thread.sleep(1000)
        println("You have " + this.coin + " coins.")
        Thread.sleep(1000)
        while (true) {
            println("Acceptable items or commands:\n")
            Thread.sleep(1000)
            for ((key, value) in SHOP_ACTIONS) {
                println("$key - $value coins")
            }
            Thread.sleep(2000)
            println("\nDo you want to purchase any items before starting the battle?\n" +
                    "Enter the card's name to purchase.\n" +
                    "Enter 'help' for prices and item instructions or 'exit' to leave the shop.")
            val action = readLine().toString()
            if (action == "help") {
                println(SHOP_INSTRUCTION)
            }
            else if (action == "exit") {
                println("Your current items:")
                Thread.sleep(1000)
                println("Buffs: " + this.buffsInventory.joinToString(" - ")) // check
                Thread.sleep(1000)
                println("Debuffs: " + this.debuffsInventory.joinToString(" - "))
                break
            }
            else {
                if (SHOP_ACTIONS.containsKey(action)) {
                    if (this.coin >= SHOP_ACTIONS[action]!!) {
                        if (action == "double" || action == "triple") {
                            this.debuffsInventory.add(action)
                        }
                        else {
                            this.buffsInventory.add(action)
                        }
                        this.coin = this.coin - SHOP_ACTIONS[action]!!
                        println("You purchase the " + action + ". You have " + this.coin + " coins left.")
                    }
                    else {
                        println("You do not have enough coin.")
                        continue
                    }
                }
                else {
                    println("Invalid item or command.")
                }
            }
        }
    }

    override fun insert() {
        while (true) {
            Thread.sleep(1000)
            println("Choose a chamber to put the bullet in:")
            val bullet = readLine()
            if (CHAMBERS.contains(bullet!!.toIntOrNull())) {
                this.insertedBullet = bullet.toInt()
                break
            }
            else {
                println("Invalid chamber. Please input a chamber from 1 to 6.")
            }
        }
        Thread.sleep(1000)
        println("Your gun is spinned.")
        this.onFire = CHAMBERS.random()
    }

    override fun useBuff() {
        Thread.sleep(2000)
        if (this.buffsInventory.isEmpty()) {
            println("You do not have any buffs to use. You have to shoot immediately.")
        }
        else {
            println("Your remaining buffs: " + this.buffsInventory.joinToString(" - "))
            Thread.sleep(1000)
            println("Do you want to use any buff before shooting?")
            while (true) {
                Thread.sleep(1000)
                println("Input the buff name to use or input 'skip' to skip using buffs.")
                val buff = readLine()
                if (this.buffsInventory.contains(buff.toString())) {
                    if (buff == "inspect") {
                        println("Checking the chamber which is on firing mode.")
                        Thread.sleep(2000)
                        // add some sort of loading text
                        println("The gun will fire on chamber " + this.onFire + ".")
                    }
                    else if (buff == "dodge") {
                        println("You are allowed to dodge in the next shot.")
                        this.buffStatus = "dodge"
                        this.times--
                    }
                    this.buffsInventory.remove(buff)
                    Thread.sleep(2000)
                    println("Your remaining buffs: " + this.buffsInventory.joinToString(" - "))
                }
                else if (buff == "skip") {
                    break
                }
                else {
                    println("You do not have this buff or enter an invalid command.")
                }
            }
        }
    }

    fun shoot() {
        super.shoot(this.times)
    }

    fun useDebuff(npc: NPC) {
        // reset effects
        this.onFire = 0
        this.times = 1
        this.insertedBullet = 0
        this.buffStatus = ""
        if (!this.isAlive) {
            // Ask player whether to restart the game
        }
        else {
            Thread.sleep(2000)
            println("Your remaining debuffs: " + this.debuffsInventory.joinToString(" - "))
            Thread.sleep(1000)
            println("Do you want to use any debuff against your opponent?")
            while (true) {
                Thread.sleep(1000)
                println("Input the debuff name to use or input 'skip' to skip using debuffs.")
                val debuff = readLine()
                if (this.debuffsInventory.contains(debuff.toString())) {
                    npc.debuffStatus = debuff.toString()
                    println("You use " + debuff.toString() + " against your opponent.")
                    Thread.sleep(2000)
                    this.debuffsInventory.remove(debuff)
                    println("Your remaining debuffs: " + this.debuffsInventory.joinToString(" - "))
                    break
                }
                else if (debuff == "skip") {
                    break
                }
                else {
                    println("You do not have this buff or enter an invalid command.")
                }
            }
        }
    }
}