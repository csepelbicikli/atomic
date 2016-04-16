

viewdir=jatek/atomic/view
modeldir=jatek/atomic/model
all: AtomicFrame.class

AtomicFrame.class: $(viewdir)/AtomicFrame.java
	javac $(modeldir)/Szin.java
	javac $(modeldir)/Jatekos.java
	javac $(modeldir)/Mezo.java
	javac $(modeldir)/Tabla.java
	javac $(viewdir)/AtomicFrame.java
	
clean:
	rm -f $(modeldir)/*.class
	rm -f $(viewdir)/*.class

