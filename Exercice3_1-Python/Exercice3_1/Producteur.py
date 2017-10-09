# coding: utf-8
from threading import Thread
import random
import time

class Producteur(Thread):
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
                if self.q.full():
                    continue
                self.interface.ajouteTexte(u"--  Producteur  --")
                value = random.randint(1,100)
                self.q.put(value)
                self.interface.ajouteTexte(u"Elément ajouté: %d"%value)
            self.waiting = True
            time.sleep(self.t)
            self.waiting = False
        self.running = False

    def interrupt(self):
        self.interrupted = True