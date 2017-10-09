# coding: utf-8
from threading import Thread
import time

class Consommateur(Thread):
    def __init__(self, interface, verrou, q, t):
        Thread.__init__(self)
        self.interrupted = False
        self.interface = interface
        self.verrou = verrou
        self.q = q
        self.t = t
        self.running = False
        self.waiting = False
        self.start()

    def run(self):
        self.running = True
        while not self.interrupted:
            with self.verrou:
                if self.q.empty():
                    continue
                self.interface.ajouteTexte(u"-- Consommateur --")
                value = self.q.get()
                self.interface.ajouteTexte(u"Elément retiré: %d"%value)
            self.waiting = True
            time.sleep(self.t)
            self.waiting = False
        self.running = False

    def interrupt(self):
        self.interrupted = True