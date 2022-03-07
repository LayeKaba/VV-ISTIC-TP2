# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](https://github.com/selabs-ur1/VV-TP2/blob/master/exercises/designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer

règle XPath : `//IfStatement//IfStatement//IfStatement`

Fichier XML :  
```XML
<?xml version="1.0"?>
<ruleset name="Custom Rules"
    xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">

    <rule name="PasTroisIf"
      language="java"
      message="Trois If imbriqués"
      class="net.sourceforge.pmd.lang.rule.XPathRule">
   <description>

   </description>
   <priority>3</priority>
   <properties>
      <property name="version" value="2.0"/>
      <property name="xpath">
         <value>
<![CDATA[
//IfStatement//IfStatement//IfStatement
]]>
         </value>
      </property>
   </properties>
</rule>
</ruleset>
```

les erreurs detectées dans le project commons-cli-master   
 - `D:\commons-cli-master\src\main\java\org\apache\commons\cli\PosixParser.java:151:        PasTroisIf:     Trois If imbriquÚs`  
```JAVA
else if (token.startsWith("--")) {
                final int pos = token.indexOf('=');
                final String opt = pos == -1 ? token : token.substring(0, pos); // --foo

                final List<String> matchingOpts = options.getMatchingOptions(opt);

                if (matchingOpts.isEmpty()) {
                    processNonOptionToken(token, stopAtNonOption);
                } else if (matchingOpts.size() > 1) {
                    throw new AmbiguousOptionException(opt, matchingOpts);
                } else {
                    currentOption = options.getOption(matchingOpts.get(0));

                    tokens.add("--" + currentOption.getLongOpt());
                    if (pos != -1) {
                        tokens.add(token.substring(pos + 1));
                    }
                }
            }

```

 - `D:\commons-cli-master\src\main\java\org\apache\commons\cli\PosixParser.java:157:        PasTroisIf:     Trois If imbriqués`  
```JAVA

 else if (token.startsWith("-")) {
                if (token.length() == 2 || options.hasOption(token)) {
                    processOptionToken(token, stopAtNonOption);
                } else if (!options.getMatchingOptions(token).isEmpty()) {
                    final List<String> matchingOpts = options.getMatchingOptions(token);
                    if (matchingOpts.size() > 1) {
                        throw new AmbiguousOptionException(token, matchingOpts);
                    }
                    final Option opt = options.getOption(matchingOpts.get(0));
                    processOptionToken("-" + opt.getLongOpt(), stopAtNonOption);
                }
                // requires bursting
                else {
                    burstToken(token, stopAtNonOption);
                }
            } else {
                processNonOptionToken(token, stopAtNonOption);
            }


```
