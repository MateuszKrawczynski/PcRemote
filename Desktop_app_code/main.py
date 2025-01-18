import flask as fl
import os
import tkinter as tk
import socket
import pyautogui as pg
import threading
import time
import webbrowser
pg.FAILSAFE = False
def start_server():
    app.run("0.0.0.0", 6482)
ip = socket.gethostbyname(socket.gethostname())
app = fl.Flask(__name__)

@app.route("/",methods=["GET"])
def home():
    return "PcRemoteServer"
@app.route("/off",methods=["GET"])
def shutdown():
    os.system("shutdown /s /t 1")
    return "Your pc shut down"
@app.route("/pause",methods=["GET"])
def pause():
    pg.press("space")
    return "Video paused/unpaused"
@app.route("/fullscreen",methods=["GET"])
def fullscreen():
    pg.press("f")
    return "Entered/Exited fullscreen"
@app.route("/next",methods=["GET"])
def nextvid():
    time.sleep(5)
    pg.keyDown('shift')
    pg.keyDown('n')
    pg.keyUp('n')
    pg.keyUp('shift')
    return "Next video"
@app.route("/youtube",methods=["GET"])
def youtube():
    webbrowser.open("https://youtube.com")
    return "Youtube opened"
@app.route("/volumeup",methods=["GET"])
def volumeup():
    pg.press("volumeup")
    return "Volume turned up"
@app.route("/volumedown",methods=["GET"])
def volumedown():
    pg.press("volumedown")
    return "Volume turned down"
@app.route("/left",methods=["GET"])
def left():
    pg.hotkey('alt', 'left')
    return ""
@app.route("/right",methods=["GET"])
def right():
    pg.hotkey('alt', 'right')
    return ""

if __name__ == "__main__":
    root = tk.Tk()
    root.title("PcRemote")
    tk.Label(root, text="PcRemote", font=("arial", 50, "normal"), fg="blue").grid(row=0, column=0)
    tk.Label(root, text="Your computer's ip is:").grid(row=1, column=0)
    tk.Label(root, text=ip).grid(row=2, column=0)
    tk.Label(root, text="If you close this window the remote will stop working").grid(row=3, column=0)
    server_thread = threading.Thread(target=start_server, daemon=True)
    server_thread.start()
    root.mainloop()


