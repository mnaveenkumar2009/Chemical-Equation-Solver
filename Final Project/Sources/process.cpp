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
inline unsigned long long uscan()
{
    unsigned long long n=0,c=gc();
while(c<'0'||c>'9')
c=gc();
while(c<='9'&&c>='0'){
n=n*10+c-'0';
c=gc();}
return n;
}
 
inline long int lscan()                 
{
    long int n=0,c=gc();
while(c<'0'||c>'9')
c=gc();
while(c<='9'&&c>='0'){
n=n*10+c-'0';
c=gc();}
return n;
}
 
 
inline  int sscan()                     
{register  int n=0,c=gc();
while(c<'0'||c>'9')
c=gc();
while(c<='9'&&c>='0')
    {
n=n*10+c-'0';
c=gc();
    }
return n;
}

int main()
{
    int i,id=0;
    
    string s;
    while(!cin.eof()){
        i=0;
        vector <string> reactants,products;
        vector <int> nor,nop;
        cin>>s;
        string reactant,product;
        while(s[i]!='=')
            if(s[i]!=' ')
                reactant.pb(s[i++]);   
        while(i!=s.length()-1)
            if(s[i]!=' ')
                product.pb(s[++i]);
        //cout<<reactant<<endl<<product<<endl;
        string number;
        f(i,reactant.length()){
            if(reactant[i]==' ')continue;
            string reactantname;
            if(reactant[i]=='('){
                i++;
                while(reactant[i]!=')'){
                    reactantname.pb(reactant[i++]);
                }
            }
        }
    }
	return 0;
}
