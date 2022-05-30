class NPC(override var name: String, val reward: Int, val script: String, val chapter: Int, val chapterInfo: String,
          var buffsInventory: Int, var debuffsInventory: MutableList<String>): IPlayerAndNPC {

    override var isAlive: Boolean = true
    override var buffStatus: String = ""
    override var debuffStatus: String = ""
    override var insertedBullet: Int = 0
    override var onFire: Int = 0
    override var times: Int = 1

    override fun insert() {
        Thread.sleep(2000)
        this.insertedBullet = CHAMBERS.random()
        println(this.name + " has inserted the bullet.")
    }

    override fun useBuff() {
        // Best case scenario for NPC: only use buff if it prevents death
        Thread.sleep(1000)
        if (this.insertedBullet == this.onFire && this.buffsInventory > 0) {
            println(this.name + " uses dodge buff and is allowed to dodge in the next shot.")
            this.buffStatus = "dodge"
            this.times--
            this.buffsInventory--
        }
        else {
            println(this.name + " does not use any buffs.")
        }
    }

    fun shoot() {
        super.shoot(this.times)
    }

    fun useDebuff(player: Player) {
        Thread.sleep(1000)
        this.onFire = 0
        this.insertedBullet = 0
        this.buffStatus = ""
        if (!this.isAlive) {
            println("Congratulations! You defeated " + this.name+ ".")
            println("You receives "+ this.reward.toString() +" coins for defeating " + this.name+ ".")
            player.coin += reward
            // maybe ask the player whether to play next chapter
        }
        else {
            if (this.debuffsInventory.size == 0) {
                println(this.name + " does not have any debuffs left.")
            }
            else {
                println(this.name + " is deciding on his debuff action.")
                Thread.sleep(2000)
                val debuff = this.debuffsInventory.random()
                println(this.name + " uses " + debuff + " debuff against you.")
                player.debuffStatus = debuff
                this.debuffsInventory.remove(debuff)
            }
        }
    }
}