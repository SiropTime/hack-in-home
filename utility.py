import difflib

from fuzzywuzzy import fuzz

import speech_recognition

DEFAULT_MESSAGE = "Извини, не смог понять, что тебе нужно :( Но я отправил коллегам на рассмотрение!"


# Сравнение двух строк на схожесть
# Необходимо для сравнения запроса пользователя с актуальными вопросами
# Имеющимися у нас в распоряжении
def similarity(s1: str, s2: str) -> float:
    n1 = s1.lower()  # Приводим к удобоваримому виду первую строку
    n2 = s2.lower()  # Аналогично со второй
    r1 = fuzz.WRatio(n1, n2) * 0.01  # Проверяем одним алгоритмом
    r2 = difflib.SequenceMatcher(None, n1, n2).ratio()  # Вторым
    res = (r1 + r2) / 2  # Для большей точности среднее берём и килдаем
    return res


def compare_commands(user_input: str, commands: list) -> str:
    """
    :param user_input: Пользовательский ввод (обработанная речь или команда, неважно
    :param commands: Список команд, чтобы сопоставить
    :return: Возвращает команду с максимальным совпадением
    """
    result_dict = {}
    for command in commands:
        try:
            sim = similarity(user_input, command)
        except AttributeError:
            continue
        if sim > 0.5:
            result_dict[command] = sim
    print(result_dict)
    if not len(result_dict.values()) == 0:
        result = max(result_dict, key=result_dict.get)
        return result
    else:
        return DEFAULT_MESSAGE


class Recognizer:
    def __init__(self):
        self.dir = ""
        self.recognizer = speech_recognition.Recognizer()

    def recognize_audio(self, audio_file: str):
        recognized_data = ""
        # Указываем файл
        with speech_recognition.AudioFile(self.dir + audio_file) as source:
            audio = self.recognizer.record(source)

        try:
            # Прогоняем через гугловское АПИ для распознавания
            recognized_data = self.recognizer.recognize_google(audio, language="ru").lower()
        except speech_recognition.UnknownValueError:
            return ""

        return recognized_data
