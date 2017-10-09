# coding: utf-8
from threading import RLock
import tkinter as tk

class Interface():
    def __init__(self, fenetre, q):
        self.fenetre = fenetre
        self.q = q
        self.file = tk.Text(fenetre,height=2,width=106,bg='gray10',fg='cyan')
        self.file.pack(anchor = tk.W)
        self.ops = tk.Text(fenetre,height=20,width=106,bg='gray10',fg='lawn green')
        self.ops.pack(anchor = tk.W)
        self.ligne = 1
        self.texte = ""
        self.verrou = RLock()

    def ajouteTexte(self, txt):
        with self.verrou:
            self.texte += txt+"\n"

    def rebuild(self):
        with self.verrou:
            texte = self.texte
            self.texte = ""
        ql = list(self.q.queue)

        self.ops.insert('%d.0'%self.ligne, texte)
        self.ligne += texte.count('\n')
        if(self.ligne > 20):
            print(self.ops.get('1.0','%d.end'%(self.ligne-20)))
            self.ops.delete('1.0','%d.0'%(self.ligne-20+1))
            self.ligne = 20

        self.file.delete('1.0','2.end')
        self.file.insert('1.0', u"File: %s\nLongueur: %d"%(str(ql),len(ql)))

        self.fenetre.after(1000, self.rebuild)