package fun.groovy;

import com.google.common.primitives.Longs;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by qingyun on 10/31/17.
 */
public class TestGroovy {
    public static void main(String[] args) {
        // move this into initialization part so that you do not call this every time.
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine  = manager.getEngineByName("groovy");
        CompiledScript script = null;
        try {
            InputStream is = TestGroovy.class.getResourceAsStream("/EmailDsl.groovy");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            script = ((Compilable) engine).compile(reader);
            script.eval();
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
