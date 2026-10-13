package LLD.DinnerPlateStacks;

public class DinnerPlateStacks {

    public static void main(String[] args) {
        DinnerPlates d = new DinnerPlates(2);

        d.push(1);
        d.push(2);
        d.push(3);
        d.push(4);
        d.push(5);
        System.out.println(d.popAtStack(0));
        d.push(20);
        d.push(21);
        System.out.println(d.popAtStack(0));
        System.out.println(d.popAtStack(2));
        System.out.println(d.pop());
        System.out.println(d.pop());
        System.out.println(d.pop());
        System.out.println(d.pop());
        System.out.println(d.pop());

        System.out.println();

        d = new DinnerPlates(1);

        d.push(1);
        d.push(2);
        System.out.println(d.pop());
        System.out.println(d.pop());
    }
}
