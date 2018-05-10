import warnings
warnings.filterwarnings('ignore')

# import logging
# logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)

from gensim.summarization import summarize


text = open("input.txt","r").read()
# print('Input text:')
# print(text)

# print('Summary:')
# print(summarize(text,ratio=0.15))

from gensim.summarization import keywords
A = (keywords(text,words=7,lemmatize=True))

s = ""
z = ""
for i in range(0,len(A)):
	if(A[i]=='\n'):
		if(z==""):
			z=s
		else:
			z = z+","+s
		s = ""
	else:
		s = s+A[i]

if(z==""): 
	z =s 
else:
	z = z+","+s


print(z)