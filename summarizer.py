
# from nltk.corpus import stopwords
# from nltk.tokenize import word_tokenize, sent_tokenize
# from nltk.stem.snowball import SnowballStemmer
# import nltk


# text = open("input.txt","r").read()



# # print(text);
# # print("=========================================================================")
# # print("=========================================================================")


# stopWords = set(stopwords.words("english"))
# words = word_tokenize(text)

# freqTable = dict()
# for word in words:
#     word = word.lower()
#     if word in stopWords:
#         continue
#     if word in freqTable:
#         freqTable[word] += 1
#     else:
#         freqTable[word] = 1


# sentences = sent_tokenize(text)
# sentenceValue = dict()

# for sentence in sentences:
#     for word, freq in freqTable.items():
#         if word in sentence.lower():
#             if sentence in sentenceValue:
#                 sentenceValue[sentence] += freq
#             else:
#                 sentenceValue[sentence] = freq



# sumValues = 0
# for sentence in sentenceValue:
#     sumValues += sentenceValue[sentence]

# # Average value of a sentence from original text
# average = int(sumValues / len(sentenceValue))


# summary = ''
# for sentence in sentences:
#     if (sentence in sentenceValue) and (sentenceValue[sentence] > (1.4*average)):
#         summary += " " + sentence

# print(summary)

import warnings
warnings.filterwarnings('ignore')

import logging
logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)

from gensim.summarization import summarize


text = open("input.txt","r").read()
# print('Input text:')
# print(text)

# print('Summary:')
print(summarize(text,word_count=80))

# from gensim.summarization import keywords
# A = (keywords(text,words=7,lemmatize=True))

# s = ""
# z = ""
# for i in range(0,len(A)):
# 	if(A[i]=='\n'):
# 		if(z==""):
# 			z=s
# 		else:
# 			z = z+","+s
# 		s = ""
# 	else:
# 		s = s+A[i]

# if(z==""): 
# 	z =s 
# else:
# 	z = z+","+s


# print(z)