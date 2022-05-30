// CONSTANT VARIABLES
var CHAMBERS = listOf(1, 2, 3, 4, 5, 6)

val SHOP_ACTIONS = mapOf("dodge" to 3,
    "inspect" to 4,
    "double" to 5,
    "triple" to 8,
    "help" to 0,
    "exit" to 0)

const val SHOP_INSTRUCTION = "\nBuff cards:\n" +
"Dodge (3 coins): Allow Player to skip a single turn.\n" +
"Inspect (4 coins): Allow Player to check the number of chamber which is on fire.\n" +
"Debuff cards:\nDouble (5 coins): Force the opponent shoots 2 times in the next turn.\n" +
"Triple (8 coins): Force the opponent shoots 3 times in the next turn.\n"

const val BACKGROUND = "It was 1942 - the peak World War II.\nGermany dominated Europe, America joined the fight.\n" +
"But the war had escalated that no single country could risk any more casualties.\n" +
"Troops were drained, leaders were desperated.\n" +
"And finally, an agreement was made.\nIt all came down to 'the leap of faith'.\n" +
"----------------------------------------\n" +
"You - a leader from the Allies, are chosen for this responsibility,\n" +
"the future of the world, lies in your hand, in the game of death:\nRUSSIAN ROULETTE.\n"

val SCRIPT = listOf("Bienvenue en Vichy France, I'm Phillipe Petain - the Chief of State.\n" +
    "Are you heading towards France to restore the power of the Allies?\n" +
    "How could you be so naive?????\nThe Axis power is too dominant - and the ally you seek is gone.\n" +
    "I will show you our prowess. Now, bring on the game!!!",

    "What leads you to the City of Rome, dares to challenge the death?\n" +
    "..........\nWell, I supposed that you have marched your way through France, with high hopes\n" +
    "With great courtesy from the Leader of Fascism and Founder of the Empire,\n" +
    "This Benito Mussolini has to say that your hopes are going to be shattered.\n" +
    "The Vichy government you proudly disbanded, was only a puppet regime.\n" +
    "For the true Axis, to expand our supremacy. Against us, you could never stand a chance.\n",

    "Prime Minister: Hail to the Emperor! HIROHITO.\n" +
    "Our great Emperor, The Attack on Pearl Harbor was a great success.\n" +
    "It declared the dominance of Japan empire to the weakening Allies. Until...\n" +
    "Emperor: Until?\nPrime Minister:.....................\n" +
    "Until the Allies commander disbanded Vichy France and Italy, with the favored from 'the game of death'.\n" +
    "Emperor: The West has lost to the game they created themselves....\n" +
    "Mussolini was too arrogant to ignore the spirits' call....\n Though, your life will not slip through my hands.\n" +
    "The odds are with me, favored by the priests, spirits, and the holy power of Kami.\n" +
    "Being The Chosen One, I bet with my life, and accept the challenge.",

    "Welcome to Nazi Germany, I'm Adolf Hitler.\n" +
    "After all these days, we finally met.\n" +
    "I guess we no longer need to have a trivial talk, do you?\n" +
    "Today is the last day of the Allies, and tomorrow is the resurgence of eternal Fascism.\n" +
    "Bring on every single of your weapons to the ultimate game of Russian Roulette.")

// OBJECT DECLARATIONS
val mainPlayer = Player()
val NPC1 = NPC("Petain", 5, SCRIPT[0], 1, "Chapter 1: Vichy France", 0, mutableListOf("double"))
val NPC2 = NPC("Mussolini", 10, SCRIPT[1], 2, "Chapter 2: Italy", 1, mutableListOf("double"))
val NPC3 = NPC("Petain", 15, SCRIPT[0], 1, "Chapter 3: Japan", 1, mutableListOf("double","triple","triple"))
val NPC4 = NPC("Petain", 100, SCRIPT[0], 1, "Chapter 4: Nazi Germany", 2, mutableListOf("double","double","triple","triple"))


fun matchLoop(Player: Player, NPC: NPC) {
    println(NPC.chapterInfo + "\n")
    Thread.sleep(1500)
    println(NPC.script + "\n")
    Thread.sleep(4000)
    while (true) {
        Player.shop()
        Player.insert()
        Player.useBuff()
        Player.shoot()
        if (!Player.isAlive) {
            println("You lost.")
            break
        }
        Player.useDebuff(NPC)
        NPC.insert()
        NPC.useBuff()
        NPC.shoot()
        if (!NPC.isAlive) {
            println("You complete Chapter" + NPC.chapter + ".")
            break
        }
        NPC.useDebuff(Player)
    }
}

fun mainGameLoop() {
    println(BACKGROUND)
    Thread.sleep(5000)
    matchLoop(mainPlayer, NPC1)
    matchLoop(mainPlayer, NPC2)
    matchLoop(mainPlayer, NPC3)
    matchLoop(mainPlayer, NPC4)
}
