package csana.incrementer.openfaas;

import csana.incrementer.base.Incrementer;

public class OpenFaas {

    public static void main(String[] args) {
        System.out.println(new Incrementer(System.getenv("REDIS_HOST")).get());
    }

}
