public class Test {

    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();
        test(evaluator, "velocity", "Error");
        test(evaluator, "velocity = -100", "Error");
        test(evaluator, "VELOCITY = 100", "Error");
        test(evaluator, "-100", "Error");
        test(evaluator, "10 ++ 20", "Error");
        
        test(evaluator, "x=y=10", "Error");
        test(evaluator, "x y=10", "Error");
        
        test(evaluator, "velocity=100", "100");
        test(evaluator, "velocity = 2 * velocity", "200");
        test(evaluator, "velocity * velocity", "40000");
        test(evaluator, "12345678901234567890+1", "12345678901234567891");
        
        test(evaluator, "99^98",
        "37346428045426946733049283396849034403602905483004151760632320446823" +
        "49196517954782034451010203315482100413928177255823637569999967368629" +
        "893450722931248929438566374350178376781666664444435951520201");
        test(evaluator, "velocity=99^98",
        "37346428045426946733049283396849034403602905483004151760632320446823" +
        "49196517954782034451010203315482100413928177255823637569999967368629" +
        "893450722931248929438566374350178376781666664444435951520201");
        test(evaluator, "velocity",
        "37346428045426946733049283396849034403602905483004151760632320446823" +
        "49196517954782034451010203315482100413928177255823637569999967368629" +
        "893450722931248929438566374350178376781666664444435951520201");
        test(evaluator, "velocity=velocity-99^98", "0");
        test(evaluator, "velocity", "0");
        test(evaluator, "0-10", "-10");
        test(evaluator, "12345678901234567890%1000", "890");
        test(evaluator, "2^3^4%1000", "352");
        test(evaluator, "7/3", "2");
    }
    
    public static void test(Evaluator e, String in, String expect) {
        try {
            System.out.println(e.evaluate(in).equals(expect) + " : " + in);
        }
        catch (Exception ex) {
            System.out.println("false : " + in);
        }
    }

}