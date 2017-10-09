# coding: utf-8
import time
    
class ThreadKiller():
    def __init__(self, fenetre, th_list):
        self.fenetre = fenetre
        self.th_list = th_list
        self.fenetre.protocol("WM_DELETE_WINDOW", self.terminate)

    def terminate(self):
        for i in range(0,len(self.th_list)):
            self.th_list[i].interrupt()

        print(u"Interruption des threads")
        i = 0
        counter = 0
        while i<len(self.th_list):
            if not self.th_list[i].running:
                print(u"Thread %d interrompu (%d secondes)"%(i,counter))
                i = i + 1
                counter = 0
            elif self.th_list[i].waiting:
                time.sleep(1)
                counter = counter + 1
            else:
                print(u"Thread %d récalcitrant"%i)
                i = i + 1
                counter = 0

        print(u"Programme Terminé")
        self.fenetre.destroy()