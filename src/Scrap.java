/* import java.util.Scanner;

public class Scrap {
    public void swap() {
        Scanner scanner = new Scanner(System.in);
        int Aa; int Ba;int Ca;int Ab;int Bb;int Cb;int Ac;int Bc;int Cc; int firstholdingNumber; int secondholdingNumber;

        System.out.println(a+","+b+","+c+","+d+","+e+","+f+","+g+","+h+","+i);
        System.out.println("Pick the number you want to swap\nType 1 for Aa, Type 2 for Ba, Type 3 for Ca"
                + "\nType 4 for Ab, Type 5 for Bb, Type 6 for Bc"
                + "\nType 7 for Ca, Type 8 for Cb, Type 9 for Cc");

        int firstswapchoice = scanner.nextInt();
        String firstswapsay;
        if (firstswapchoice == 1){
            firstswapsay ="Aa";
            firstholdingNumber = a;
        }
        else if(firstswapchoice==2){
            firstswapsay ="Ba";
            firstholdingNumber = b;
        }
        else if(firstswapchoice==3){
            firstswapsay ="Ca";
            firstholdingNumber = c;
        }
        else if(firstswapchoice==4){
            firstswapsay ="Ab";
            firstholdingNumber = d;
        }
        else if(firstswapchoice==5){
            firstswapsay ="Bb";
            firstholdingNumber = e;
        }
        else if(firstswapchoice==6){
            firstswapsay ="Cb";
            firstholdingNumber = f;
        }
        else if(firstswapchoice==7){
            firstswapsay ="Ac";
            firstholdingNumber = g;
        }
        else if(firstswapchoice==8){
            firstswapsay ="Ab";
            firstholdingNumber = h;
        }
        else if(firstswapchoice==9){
            firstswapsay ="Ac";
            firstholdingNumber = i;
        }
        else{
            firstswapsay ="IDK";
            firstholdingNumber = 1;
            System.out.println("I said give me a number from 1-9 stupid");
        }
        System.out.println("Pick the number you want to swap " + firstswapsay + " with... \nType 1 for Aa, Type 2 for Ba, Type 3 for Ca"
                + "\nType 4 for Ab, Type 5 for Bb, Type 6 for Bc"
                + "\nType 7 for Ca, Type 8 for Cb, Type 9 for Cc");
        int secondswapchoice = scanner.nextInt();
        String secondswapsay;

        if(secondswapchoice==1){
            secondholdingNumber = a;
            secondswapsay = "Aa";
        }
        else if(secondswapchoice==2){
            secondholdingNumber = b;
            secondswapsay = "Ba";
        }
        else if(secondswapchoice==3){
            secondholdingNumber = c;
            secondswapsay = "Ca";
        }
        else if(secondswapchoice==4){
            secondholdingNumber = d;
            secondswapsay = "Ab";
        }
        else if(secondswapchoice==5){
            secondholdingNumber = e;
            secondswapsay = "Bb";
        }
        else if(secondswapchoice==6){
            secondholdingNumber = f;
            secondswapsay = "Cb";
        }
        else if(secondswapchoice==7){
            secondholdingNumber = g;
            secondswapsay = "Ca";
        }
        else if(secondswapchoice==8){
            secondholdingNumber = h;
            secondswapsay = "Cb";
        }
        else if(secondswapchoice==9){
            secondholdingNumber = i;
            secondswapsay = "Cc";
        }
        else {
            System.out.println("I said give me a number from 1-9 stupid");
            secondholdingNumber = 1;
            secondswapsay = "idk";
        }
        // Swap Logic
        System.out.println("Swapping...:");
        int temp = firstholdingNumber;
        firstholdingNumber = secondholdingNumber;
        secondholdingNumber = temp;

        System.out.println("\n" + firstholdingNumber);
        System.out.println("\n" + secondholdingNumber);


        System.out.println("SWAP COMPLETE!:"+firstswapsay+" and "+secondswapsay+" have been swapped! \n    A   B   C\n" + " a [" + a + "] [" + b + "] [" + c + "]\n b [" + d + "] [" + e + "] [" + f + "]\n c [" + g + "] [" + h + "] [" + i + "]"
                + "\n   MONEY: " + money + " \n   CARDS: " + Card1+", "+Card2+", "+Card3+", "+Card4);
    }
}
*/