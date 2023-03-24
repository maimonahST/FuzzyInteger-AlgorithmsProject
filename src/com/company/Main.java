package com.company;
import java.util.Random;


public class Main {
    static Random rand=new Random();
    public static void main(String[] args) {

        int size=10;
        //variable for probablity eqaution
        int LB[]=new int[size];
        int UB[]=new int[size];

        int sorted[]={3,5,2,8,6,1,0,9,4,7};

        //generate
        for(int i=0;i<size;i++){
            LB[i]=rand.nextInt(10);
            UB[i]=rand.nextInt(10);
            int temp=0;
            if (LB[i]>UB[i]){
                temp=LB[i];
                LB[i]=UB[i];
                UB[i]=temp;
            }
        }

        //print
        print(LB,UB,sorted);

        // apply the algorithms
        MathematicalCalc(sorted,LB,UB);
        BruteForce(sorted,LB,UB);
    }

    static double equation( int k,int s,  int n ,int b, int a ){

        return (s*n)-(k*a+(k*(k-1))/(2.0))-(b*n);
    }

    static void print(int [] LB , int [] UB, int [] sorted){
        int size = LB.length;

        System.out.println("-------- ranges in random generating order --------");
        System.out.print("Lower ");
        for(int i=0;i<size;i++){
            System.out.print(LB[i]+" ");
        }

        System.out.print("\nUpper ");
        for(int i=0;i<size;i++){
            System.out.print(UB[i]+" ");
        }

        System.out.println("\n-------- ranges in sorted order --------");
        System.out.print("Lower ");
        for(int i=0;i<size;i++){
            System.out.print(LB[sorted[i]]+" ");
        }

        System.out.print("\nUpper ");
        for(int i=0;i<size;i++){
            System.out.print(UB[sorted[i]]+" ");
        }

        System.out.println();
    }

    static void BruteForce(int [] sorted, int [] LB , int [] UB ){
        double numOfTrue;
        double prop = 1;
        for (int i = 0; i < sorted.length-1; i++){
            numOfTrue = 0 ;
            for (int  j = LB[sorted[i]] ; j <= UB[sorted[i]]; j++) {
                for (int k = LB[sorted[i + 1]]; k <= UB[sorted[i + 1]]; k++) {
                    if (j <= k) {
                        numOfTrue++;
                    }
                }
            }
            int s  = UB[sorted[i]]-LB[sorted[i]]+1;
            int n  = UB[sorted[i+1]]-LB[sorted[i+1]]+1;
            prop = prop * (numOfTrue/(s*n));

            if( numOfTrue == 0){
                System.out.println("probability of sorting (Brute Force)  :  " + 0.0 + "%" );
                System.out.println("impossible to be sorted!");
                return;
            }
        }

        System.out.println("probability of sorting (Brute Force) :  " + prop*100 + "%" );
    }

    static void MathematicalCalc(int [] sorted, int [] LB , int [] UB ){
        int size = sorted.length;
        double propbabilty=1, result = 0;
        int k,s,b,n,a;
        for(int i=0;i<size-1;i++){
            //case 1 :imposiple sorted
            if ((UB[sorted[i]]>LB[sorted[i+1]])&&(LB[sorted[i]]>UB[sorted[i+1]])){
                propbabilty=propbabilty*0;
                break;
            }else if (UB[sorted[i]]<=LB[sorted[i+1]]){//case 2: no overlap (100%) correct
                propbabilty=propbabilty*1;
            }else if(LB[sorted[i]] <= UB[sorted[i+1]] ||  LB[sorted[i+1]] <= UB[sorted[i]]  ) {
                //case 3: overlap in some numbers
                s=(UB[sorted[i]]-LB[sorted[i]])+1;
                n=(UB[sorted[i+1]]-LB[sorted[i+1]])+1;
                int LBmax= Math.max(LB[sorted[i]],LB[sorted[i+1]]);
                int UBmin= Math.min(UB[sorted[i]],UB[sorted[i+1]]);
                a=(LB[sorted[i]]-LB[sorted[i+1]]);
                if(a < 0 ){ a = 0; }
                b=(UB[sorted[i]]-UB[sorted[i+1]]);
                if(b < 0 ){ b = 0; }
                k=UBmin-LBmax+1;
                result=equation(k,s,n,b,a);
                //calculate successful of sorted:
                propbabilty=propbabilty*(result/(n*s));
            }else{
                System.out.println("this case not cover yet");
            }
        }
        System.out.println("probability of sorting ( MathematicalCalc) :"+(propbabilty*100)+"%");
        if(propbabilty==0){
            System.out.println("impossible to be sorted!");
        }
    }

}



