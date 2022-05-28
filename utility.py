from fuzzywuzzy import fuzz
import speech_recognition


# Сравнение двух строк на схожесть
# Необходимо для сравнения запроса пользователя с актуальными вопросами
# Имеющимися у нас в распоряжении
def similarity(s1: str, s2: str):
    n1 = s1.lower()  # Приводим к удобоваримому виду первую строку
    n2 = s2.lower()  # Аналогично со второй
    return fuzz.WRatio(n1, n2)*0.01


def compare_commands(user_input: str, commands: list) -> str:
    """
    :param user_input: Пользовательский ввод (обработанная речь или команда, неважно
    :param commands: Список команд, чтобы сопоставить
    :return: Возвращает команду с максимальным совпадением
    """
    result_dict = {}
    for command in commands:
        result_dict[command] = similarity(user_input, command)
    result = max(result_dict, key=result_dict.get)
    return result


class Recognizer:
    def __init__(self):
        self.dir = "audios/"
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
