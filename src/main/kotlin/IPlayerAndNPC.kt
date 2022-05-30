interface IPlayerAndNPC {
    // interface properties
    var name: String
    var isAlive: Boolean
    var buffStatus: String
    var debuffStatus: String
    var insertedBullet: Int
    var onFire: Int
    var times: Int

    // interface functions
    fun insert()
    fun useBuff()
    fun shoot(times: Int) {
        Thread.sleep(1000)
        var thisTimes = times
        if (this.debuffStatus == "double") thisTimes += 1
        else if (this.debuffStatus == "triple") thisTimes += 2
        this.debuffStatus = ""
        if (thisTimes == 0) println(this.name + " do not have any shots left to shoot.")
        else {
            println("The gun is spinned. " + this.name + " has " + thisTimes.toString() + " shot(s) left.")
            Thread.sleep(2000)
            if (this.onFire == this.insertedBullet) {
                println("BOOM!" + this.name + "died.")
                this.isAlive = false
            }
            else {
                println(this.name + " luckily survived.")
                this.onFire = CHAMBERS.random()
                shoot(thisTimes - 1)
            }
        }
    }
}






