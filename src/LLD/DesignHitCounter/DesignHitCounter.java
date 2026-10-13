package LLD.DesignHitCounter;

public class DesignHitCounter {

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        System.out.println(counter.getHits(301));

        System.out.println();

        counter = new HitCounter();
        counter.hit(1);
        System.out.println(counter.getHits(1));
        System.out.println(counter.getHits(301));

        System.out.println();

        counter = new HitCounter();
        counter.hit(1);
        counter.hit(1);
        counter.hit(1);
        counter.hit(1);
        counter.hit(1);

        System.out.println(counter.getHits(1));
    }
}
