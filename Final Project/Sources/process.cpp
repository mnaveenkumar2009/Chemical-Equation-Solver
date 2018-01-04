#include <bits/stdc++.h>
using namespace std;
#define f(i,n) for(i=0;i<n;i++)
#define pb push_back
#define sd(n) scanf("%d",&n)
#define sc(n) scanf("%c",&n)
#define slld(n) scanf("%lld",&n)
#define mod 1000000007
#define mp make_pair
#define ff first
#define ss second
#define ll long long
#define gc getchar
#define pc putchar
int convertToNumber(string number){
    if(number=="")return 0;
    int ans=0,i=0;
    char c=number[i++];
    while(c<'0'||c>'9')
        if(i==number.length())
            return 0;
        else
            c=number[i++];
    while(c<='9'&&c>='0')
    {
        ans=ans*10+c-'0';
        if(i==number.length())return ans;
        c=number[i++];
    }
    return ans;
}

int main()
{
    FILE * input;
    input=fopen("source.txt","r");
    FILE * output;
    output= fopen("reactions.txt","r");
    char s[1000];
    int count=0;
    set<vector <string> > reactions;

    while(fscanf(output,"%[^\n]%*c",s)==1){

        if(!count){
            vector <string> temp;
            string ss;
            int i,n=strlen(s);
            f(i,n){
                if(s[i]!=' ')
                ss.pb(s[i]);
                else
                {
                    if(ss.length()!=0)
                    temp.pb(ss);
                    ss.erase(ss.begin(),ss.end());
                }
            }
	        if(ss.length()!=0)
                temp.pb(ss);	
            sort(temp.begin(),temp.end());
            reactions.insert(temp);
        }
        count++;
        count%=4;
    }
    fclose(output);
    output= fopen("reactions.txt","w");
    while(fscanf(input,"%[^\n]%*c",s)==1){
        vector <string> reactants,products;
        vector <int> nor,nop;
        int i=0;
        string reactant,product;
        while(s[i]!='=')
            if(s[i]!=' ')
                reactant.pb(s[i++]);   
        while(i!=strlen(s)-1)
            if(s[i]!=' ')
                product.pb(s[++i]);
                
        string temp="";
        f(i,reactant.length()){
            if(reactant[i]==' ')continue;
            string reactantname="";
            if(reactant[i]=='('){
                i++;
                int countleftB=1,countrightB=0;
                while(countleftB!=countrightB){
                    if(reactant[i]=='(')countleftB++;
                    if(reactant[i]==')')countrightB++;
                    if(countleftB==countrightB)break;
                    reactantname+=reactant[i];
                    i++;
                }
                int tempN=convertToNumber(temp);

                //cout<<temp<<endl;
                //cout<<tempN<<reactantname<<endl;
                temp="";
            }
            else
            {
                temp+= reactant[i];
            }
        }        
    }
    
    fclose(input);
    fclose(output);
        

	return 0;
}
