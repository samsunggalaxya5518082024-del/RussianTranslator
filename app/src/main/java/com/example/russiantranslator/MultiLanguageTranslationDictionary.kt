package com.example.russiantranslator

object MultiLanguageTranslationDictionary {
    private val dictionary = mapOf(
        "hello" to "привет",
        "hi" to "привет",
        "goodbye" to "до свидания",
        "bye" to "пока",
        "thanks" to "спасибо",
        "thank" to "спасибо",
        "yes" to "да",
        "no" to "нет",
        "ok" to "окей",
        "okay" to "окей",
        "settings" to "настройки",
        "search" to "поиск",
        "home" to "главная",
        "back" to "назад",
        "next" to "далее",
        "error" to "ошибка",
        "warning" to "предупреждение",
        "help" to "помощь",
        "save" to "сохранить",
        "delete" to "удалить",
        "cancel" to "отмена",
        "confirm" to "подтвердить",
        "open" to "открыть",
        "close" to "закрыть",
        "login" to "вход",
        "password" to "пароль",
        "user" to "пользователь",
        "message" to "сообщение",
        "email" to "электронная почта",
        "app" to "приложение",
        "application" to "приложение",
        "button" to "кнопка",
        "menu" to "меню",
        "language" to "язык",
        "translation" to "перевод",
        "translate" to "перевести",
        "start" to "начать",
        "stop" to "остановить",
        "enabled" to "включено",
        "disabled" to "отключено",
        "network" to "сеть",
        "download" to "скачать",
        "upload" to "загрузить",
        "internet" to "интернет",
        "welcome" to "добро пожаловать",
        "friend" to "друг",
        "family" to "семья",
        "today" to "сегодня",
        "tomorrow" to "завтра",
        "yesterday" to "вчера",
        "please" to "пожалуйста",
        "sorry" to "извините",
        "account" to "аккаунт",
        "profile" to "профиль",
        "chat" to "чат",
        "call" to "вызов",
        "phone" to "телефон",
        "screen" to "экран",
        "text" to "текст",
        "document" to "документ",
        "file" to "файл",
        "folder" to "папка",
        "image" to "изображение",
        "share" to "поделиться",
        "copy" to "копировать",
        "paste" to "вставить",
        "cut" to "вырезать"
    )

    fun translate(raw: String): String? = dictionary[raw.trim().lowercase()]

    fun translateText(raw: String): String {
        if (raw.isBlank()) return raw
        return raw.split(Regex("(\\s+|[.,!?;:\\-()\\[\\]{}])")).joinToString("") { part ->
            if (part.isBlank()) part else translate(part) ?: part
        }
    }

    fun getAvailableLanguages(): List<String> = listOf(
        "English", "Spanish", "French", "German", "Japanese",
        "Korean", "Arabic", "Chinese", "Turkish", "Italian"
    )
}
