import os

import requests
from pydub import AudioSegment
from flask import Flask, jsonify, request, json, flash

from utility import Recognizer, compare_commands, DEFAULT_MESSAGE

app = Flask("TextRecognizer")
app.config['JSON_AS_ASCII'] = False
app.config['UPLOAD_FOLDER'] = 'audios/'
MAIN_BACK_URL = "http://25.6.173.125:8080"  # URL на Данин бэк
recognizer = Recognizer()

requests_session = requests.session()
requests_session.headers.update({'Content-Type': 'application/json'})
requests_session.headers.update({'charset': 'utf-8'})


@app.route("/")
def home():
    return "Home"


@app.route("/listen", methods=['POST'])
def process_audio():
    if request.method == 'POST':
        if 'file' not in request.files:
            flash('No file part')
            return "Please, send audio file"

        file = request.files['file']
        file.save("audios/voice.wav")
        voice = AudioSegment.from_file("audios/voice.wav")
        voice.export("audios/ready.flac", format="flac")
        message = recognizer.recognize_audio("audios/ready.flac")
        print(message)
        response = requests_session.get(MAIN_BACK_URL + "/faq/all")
        json_response = response.json()
        questions = [x["question"] for x in json_response]

        result_command = compare_commands(message, questions)
        if result_command == DEFAULT_MESSAGE:
            requests_session.post(MAIN_BACK_URL + "/faq/save", json={"question": message})
            return {"sender": "bot", "text": result_command}, 200
        result_answer = ""
        for item in json_response:
            if item["question"] == result_command:
                result_answer = item["answer"]
                break

        if result_command:
            return {"sender": "bot", "text": result_answer}, 200

        else:
            # Аналогичная причина
            return {"text": "Ничего не найдено"}, 202


@app.route("/end", methods=['GET'])
def endpoint():
    return {"sender": "bot", "text": "Да в хуй его не ебу че меня спрашивают, отъебись студент"}


@app.route("/message", methods=['POST'])
def process_message():
    if request.method == 'POST':
        if request.is_json:
            message = request.get_json(force=True)['text']

            response = requests_session.get(MAIN_BACK_URL + "/faq/all")
            json_response = response.json()
            questions = [x["question"] for x in json_response]

            result_command = compare_commands(message, questions)
            if result_command == DEFAULT_MESSAGE:
                requests_session.post(MAIN_BACK_URL + "/faq/save", json={"question": message})
                return {"sender": "bot", "text": result_command}, 200
            result_answer = ""
            for item in json_response:
                if item["question"] == result_command:
                    result_answer = item["answer"]
                    break

            if result_command:
                return {"sender": "bot", "text": result_answer}, 200

            else:
                # Аналогичная причина
                return {"text": "Ничего не найдено"}, 202
    else:
        return "ХЗ"


import numpy as np


def wav2pcm(wavfile, pcmfile, data_type=np.int16):
    f = open(wavfile, "rb")
    f.seek(0)
    f.read(44)
    data = np.fromfile(f, dtype=data_type)
    data.tofile(pcmfile)


if __name__ == '__main__':
    app.debug = True
    app.run(debug=True, port=8080, host="0.0.0.0")
