# coding: utf-8
from threading import RLock
import tkinter as tk
import queue

from Exercice3_1.ThreadKiller import ThreadKiller
from Exercice3_1.Interface import Interface
from Exercice3_1.Producteur import Producteur
from Exercice3_1.Consommateur import Consommateur

if __name__ == "__main__":
    fenetre = tk.Tk()
    fenetre.title("File d'entiers")
    fenetre.geometry("852x360")

    q = queue.Queue(20)
    interface = Interface(fenetre, q)
    verrou = RLock()

    th1 = Producteur(interface, verrou, q, 0.450)
    th2 = Consommateur(interface, verrou, q, 1)
    th3 = Consommateur(interface, verrou, q, 1)

    ThreadKiller(fenetre, [th1,th2,th3])

    fenetre.after(0, interface.rebuild)
    fenetre.mainloop()