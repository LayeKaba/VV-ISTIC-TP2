import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParserGetter{

	public static List<String> attributs = new ArrayList<>();
	public static List<String> methodes = new ArrayList<>();
	public static List<String> attributsSansGetter = new ArrayList<>();
	public static void main(String[] args) throws ParseException {
		String nomFichier = "test/Person.java";

		analyze(new File(nomFichier));
		for (String at : attributs){
			String cap = at.substring(0, 1).toUpperCase() + at.substring(1);
			if (!methodes.contains("get"+cap)){
				attributsSansGetter.add(at);
			}
		}

		if (attributsSansGetter.isEmpty()){
			System.out.println("Aucun attribut sans getter");
		} else {
			System.out.println("Les attributs sans getter : ");
			for (String s : attributsSansGetter){
				System.out.println(s);
			}
		}
	}

	public  static void analyze(File source) throws  ParseException {
		CompilationUnit cu = StaticJavaParser.parse(String.valueOf(source));
		cu.accept(new VoidVisitorAdapter<List<String>>() {

			@Override
			public void visit(MethodDeclaration md, FieldDeclaration fd,List<EntityField> f) {
				if (md.isPublic() && md.getName().toString().startsWith("get") && !md.getType().isVoidType()) {
					methodes.add(md.getName().toString());
				}
			}

			public void visit(FieldDeclaration fd, List<EntityField> f) {
				if (fd.isPrivate()){
						attributs.add(fd.getVariables().get(0).getName().toString());
					}
			}
		}, "");
	}

}
