package com.example.simpletodolist.model

fun randomTitle(): String {
    val titles = listOf(
        "Einkaufsliste schreiben",
        "Wohnung aufräumen",
        "Zahnarzttermin vereinbaren",
        "Rezept für Brille abholen",
        "Vokabeln lernen",
        "Mit dem Hund spazieren gehen",
        "An Oma schreiben",
        "Versicherungsunterlagen prüfen",
        "Steuererklärung vorbereiten",
        "Kinoabend planen",
        "Fahrradreifen flicken",
        "Keller entrümpeln",
        "Geschenk für Papa besorgen",
        "Zugticket buchen",
        "Balkonpflanzen gießen",
        "Kleiderschrank ausmisten",
        "Ölwechseltermin vereinbaren",
        "Neue Rezepte ausprobieren",
        "Altes Handy verkaufen",
        "Endlich mal wieder schwimmen gehen",
        "Fotoalbum ordnen",
        "Für den Halbmarathon trainieren",
        "Museumsbesuch planen",
        "Konzerttickets kaufen",
        "Überraschungsparty organisieren"
    )

    return titles.random()
}

fun randomSubject(): String {
    val subjects = listOf(
        "Fang einfach an!",
        "Kleine Schritte führen auch zum Ziel.",
        "Du schaffst das!",
        "Nicht aufgeben!",
        "Jeder Tag ist eine neue Chance.",
        "Träume nicht dein Leben, lebe deinen Traum.",
        "Ausreden kennen dich, Erfolg aber nicht.",
        "Wer kämpft, kann verlieren. Wer nicht kämpft, hat schon verloren.",
        "Motivation bringt dich in Gang, Disziplin hält dich am Laufen.",
        "Erfolg ist kein Zufall.",
        "Fokussiere dich auf das Machbare, nicht auf das Unmögliche.",
        "Warte nicht auf den perfekten Moment, nimm den Moment und mach ihn perfekt.",
        "Wenn es einfach wäre, würde es jeder tun.",
        "Du wirst es später bereuen, es nicht versucht zu haben.",
        "Ziele sind Träume mit Termin.",
        "Erfolg besteht darin, von einem Fehlschlag zum anderen zu gehen, ohne seine Begeisterung zu verlieren.",
        "Vergleiche dich nicht mit anderen, vergleiche dich mit dem, der du gestern warst.",
        "Es ist nie zu spät, das zu werden, was du hättest sein können.",
        "Dein größter Gegner bist du selbst.",
        "Es gibt kein 'Ich kann nicht', nur 'Ich will nicht'.",
        "Gib jedem Tag die Chance, der schönste deines Lebens zu werden.",
        "Der Unterschied zwischen gewöhnlich und außergewöhnlich ist das kleine Extra.",
        "Tue heute etwas, wofür dein zukünftiges Ich dir danken wird.",
        "Nur wer sein Ziel kennt, findet den Weg.",
        "Auch der längste Weg beginnt mit dem ersten Schritt."
    )
    return subjects.random()
}

