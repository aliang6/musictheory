#! usr/bin/python

x = 2
x = "lsdkjfkdlsjfjk"


x = [ ] 

y = [1]*5 # [1,1,1,1,1]

y[1] = 2

print (y)
y.append(9)

print (y)

def po ( y, u, p= None):

	pass
print (x) 

accidentals = []
noteDictionary = []
notes = []
perfectInterval = []
majorInterval = []

accidentals = ["bb", "b", "n", "#", "x"]
noteDictionary = ["GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G", "GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G"]
notes = ["A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G"]
perfectInterval = ["d", "P", "A"]
majorInterval = ["d", "m", "M", "A"]


#Helper Functions===========================
def findNote (note):
	for x in notes:
		if x == note:
			print (x)
			return x
			break

