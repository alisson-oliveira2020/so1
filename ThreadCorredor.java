package controller;
 
import java.util.concurrent.Semaphore;
 
public class ThreadCorredor extends Thread {
 
    private int idPessoa;
    private static int posicaoChegada;
    private Semaphore semaforo;
 
    public ThreadCorredor(int idPessoa, Semaphore semaforo) {
        this.idPessoa = idPessoa;
        this.semaforo = semaforo;
    }
 
    @Override
    public void run() {
        Andando();
        try {
            semaforo.acquire();
            AbrindoPorta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
 
    private void Andando() {
        int distanciaTotal = 200;
        int distanciaPercorrida = 0;
        int deslocamento = (int) ((Math.random() * 2) + 4);
        int tempo = 1000;
        while (distanciaPercorrida < distanciaTotal) {
            distanciaPercorrida += deslocamento;
            try {
                sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A pessoa #" + idPessoa + " andou " + distanciaPercorrida + " m.");
        }
        posicaoChegada++;
        System.out.println("A pessoa #" + idPessoa + " foi a " + posicaoChegada + "ª a chegar");
    }
 
    private void AbrindoPorta() {
        int tempo = (int) ((Math.random() * 1000) + 1000);
        try {
            sleep(tempo);
            System.out.println("A pessoa #" + idPessoa + " abriu e cruzou a porta.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
}
