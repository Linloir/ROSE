package parser;

import env.*;
import exceptions.*;
import lexer.*;
import lexer.Number;
import logger.*;
import type.*;

import java.io.*;
import java.util.*;

public class Parser {
    private Env env;
    private TokenHandle tokenHandle;
    private Token lookahead;
    
    private class _r_module {
        public flowchart.Module g_mod;
        public _r_module(flowchart.Module g_mod) {
            this.g_mod = g_mod;
        }
    }

    private class _r_type {
        public Type val;
        public _r_type(Type val) {
            this.val = val;
        }
    }

    private class _r_type_arr {
        public Type val;
        public _r_type_arr(Type val) {
            this.val = val;
        }
    }

    private class _r_type_rec {
        public Type val;
        public _r_type_rec(Type val) {
            this.val = val;
        }
    }

    private class _r_field_list {
        public Hashtable<String, Declaration> fields;
        public _r_field_list(Hashtable<String, Declaration> fields) {
            this.fields = fields;
        }
    }

    private class _r_id_list {
        public ArrayList<Identifier> ids;
        public _r_id_list(ArrayList<Identifier> ids) {
            this.ids = ids;
        }
    }

    private class _r_proc_head {
        public Identifier id;
        public Declaration decl;
        public _r_proc_head(Identifier id, Declaration decl) {
            this.id = id;
            this.decl = decl;
        }
    }

    private class _r_proc_pars {
        public ArrayList<Declaration> params;
        public _r_proc_pars(ArrayList<Declaration> params) {
            this.params = params;
        }
    }

    private class _r_par_list {
        public ArrayList<Declaration> params;
        public _r_par_list(ArrayList<Declaration> params) {
            this.params = params;
        }
    }

    private class _r_par {
        public ArrayList<Declaration> params;
        public _r_par(ArrayList<Declaration> params) {
            this.params = params;
        }
    }

    private class _r_ext_par {
        public ArrayList<Declaration> params;
        public _r_ext_par(ArrayList<Declaration> params) {
            this.params = params;
        }
    }

    private class _r_ext_id {
        public ArrayList<Identifier> ids;
        public _r_ext_id(ArrayList<Identifier> ids) {
            this.ids = ids;
        }
    }

    private class _r_assignment {
        public flowchart.PrimitiveStatement statement;
        public _r_assignment(flowchart.PrimitiveStatement statement) {
            this.statement = statement;
        }
    }

    private class _r_sel {
        public String text;
        public Type type;
        public _r_sel(String text, Type type) {
            this.text = text;
            this.type = type;
        }
    }

    private class _r_proc_call {
        public flowchart.PrimitiveStatement statement;
        public _r_proc_call(flowchart.PrimitiveStatement statement) {
            this.statement = statement;
        }
    }

    private class _r_act_pars {
        public ArrayList<Type> types;
        public String text;
        public _r_act_pars(ArrayList<Type> types, String text) {
            this.types = types;
            this.text = text;
        }
    }

    private class _r_expr_list {
        public ArrayList<Type> types;
        public String text;
        public _r_expr_list(ArrayList<Type> types, String text) {
            this.types = types;
            this.text = text;
        }
    }

    private class _r_ext_expr {
        public ArrayList<Type> types;
        public String text;
        public _r_ext_expr(ArrayList<Type> types, String text) {
            this.types = types;
            this.text = text;
        }
    }

    private class _r_if_stat {
        public flowchart.IfStatement statement;
        public _r_if_stat(flowchart.IfStatement statement) {
            this.statement = statement;
        }
    }

    private class _r_elif_seq {
        public SeqHandle g_pstats;
        public _r_elif_seq(SeqHandle g_pstats) {
            this.g_pstats = g_pstats;
        }
    }

    private class _r_while_stat {
        public flowchart.WhileStatement statement;
        public _r_while_stat(flowchart.WhileStatement statement) {
            this.statement = statement;
        }
    }

    private class _r_expr {
        public Type type;
        public String text;
        public _r_expr(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_simple_expr {
        public Type type;
        public String text;
        public _r_simple_expr(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_post_expr {
        public Type type;
        public String text;
        public _r_post_expr(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_rel_op {
        public String text;
        public Operator op;
        public _r_rel_op(String text, Operator op) {
            this.text = text;
            this.op = op;
        }
    }

    private class _r_post_term {
        public Type type;
        public String text;
        public _r_post_term(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_term {
        public Type type;
        public String text;
        public _r_term(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_factor {
        public Type type;
        public String text;
        public _r_factor(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private class _r_post_factor {
        public Type type;
        public String text;
        public _r_post_factor(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    public Parser(TokenHandle handle) throws IOException, OberonException {
        this.env = new Env(null);
        this.tokenHandle = handle;
        this.lookahead = tokenHandle.nextToken();

        ArrayList<Declaration> decls = new ArrayList<Declaration>();
        Hashtable<String, Object> extAttrs = new Hashtable<String, Object>();
        extAttrs.put("modifiers", new Declaration.Modifiers[] { Declaration.Modifiers.VAR });
        decls.add(new Declaration(new Identifier("x"), new IntegerType(), extAttrs));
        this.env.putDecl("Read", new Declaration(new Identifier("Read"), new ProcedureType(decls), null));

        decls = new ArrayList<Declaration>();
        decls.add(new Declaration(new Identifier("x"), new IntegerType(), null));
        this.env.putDecl("Write", new Declaration(new Identifier("Write"), new ProcedureType(decls), null));

        decls = new ArrayList<Declaration>();
        this.env.putDecl("WriteLn", new Declaration(new Identifier("WriteLn"), new ProcedureType(decls), null));
    }

    public void parse() throws IOException, OberonException {
        Logger.debug("Start: parse");
        Logger.debug("Lookahead: " + lookahead.toString());
        Logger.debug("Use: default");

        Env cur = env;
        env = new Env(env);

        flowchart.Module g_mod = module().g_mod;

        match(Symbol.eof(), Symbol.class);

        g_mod.show();

        env = cur;

        Logger.debug("End: parse");
    }

    private _r_module module() throws IOException, OberonException {
        Logger.debug("Start: module");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symModule())) {
            Logger.debug("Use: eq1");

            match(Keyword.symModule(), Keyword.class);

            Identifier id = match(new Identifier(), Identifier.class);

            match(Symbol.semicol(), Symbol.class);

            Declaration decl = new Declaration(id, new ModuleType(), null);
            try {
                env.putDecl(id.getLexeme(), decl);
            } catch (OberonException e) {
                throw new UnknownIdentifier(id.getLexeme(), id.getLine(), id.getColumn());
            }
            flowchart.Module g_mod = new flowchart.Module(id.getLexeme());
            Env cur = env;
            env = new Env(env);

            decls(g_mod);

            flowchart.Procedure g_proc = g_mod.add("Main");

            stat_block(g_proc::add);

            match(Keyword.symEnd(), Keyword.class);
            
            Identifier endId = match(new Identifier(), Identifier.class);

            if (!id.getLexeme().toLowerCase().equals(endId.getLexeme().toLowerCase())) {
                throw new MismatchedDeclaration("MODULE", id, endId);
            }

            match(Symbol.dot(), Symbol.class);
            
            env = cur;

            Logger.debug("End: module");
            return new _r_module(g_mod);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symModule()
            }, lookahead);
        }
    }

    private void stat_block(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: stat_block");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symBegin())) {
            Logger.debug("Use: eq1");

            match(Keyword.symBegin(), Keyword.class);

            stat_seq(g_stats);

            Logger.debug("End: stat_block");
        }
        else if (lookahead.equals(Keyword.symEnd())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: stat_block");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void decls(flowchart.Module g_mod) throws IOException, OberonException {
        Logger.debug("Start: decls");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symConst()) ||
            lookahead.equals(Keyword.symType()) ||
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            Logger.debug("Use: eq1");

            cnst_decl();
            type_decl();
            var_decl();
            proc_decl_seq(g_mod);

            Logger.debug("End: decls");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symProcedure(),
                Keyword.symConst(),
                Keyword.symType(),
                Keyword.symVar(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void cnst_decl() throws IOException, OberonException {
        Logger.debug("Start: cnst_decl");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symConst())) {
            Logger.debug("Use: eq1");

            match(Keyword.symConst(), Keyword.class);
            cnst_ass();

            Logger.debug("End: cnst_decl");
        }
        else if (
            lookahead.equals(Keyword.symType()) ||
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: cnst_decl");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symConst(),
                Keyword.symType(),
                Keyword.symVar(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void cnst_ass() throws IOException, OberonException {
        Logger.debug("Start: cnst_ass");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);
            
            Operator op = match(Operator.eq(), Operator.class);

            Type type = expr().type;
            if (!(
                type instanceof IntegerType ||
                type instanceof BooleanType
            )) {
                throw new UnexpectedType(op, new Type[] { new IntegerType(), new BooleanType() }, type);
            }

            Hashtable<String, Object> extAttrs = new Hashtable<String, Object>();
            extAttrs.put("modifiers", new Declaration.Modifiers[] { Declaration.Modifiers.CONST });
            Declaration decl = new Declaration(id, type, extAttrs);
            try {
                env.putDecl(id.getLexeme(), decl);
            } catch (OberonException e) {
                Declaration prev = env.getDecl(id.getLexeme());
                throw new IdentifierAlreadyExists(id, prev.getId());
            }

            match(Symbol.semicol(), Symbol.class);

            cnst_ass();

            Logger.debug("End: cnst_ass");
        }
        else if (
            lookahead.equals(Keyword.symType()) ||
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: cnst_ass");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                Keyword.symType(),
                Keyword.symVar(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void type_decl() throws IOException, OberonException {
        Logger.debug("Start: type_decl");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symType())) {
            Logger.debug("Use: eq1");

            match(Keyword.symType(), Keyword.class);

            type_ass();

            Logger.debug("End: type_decl");
        }
        else if (
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: type_decl");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symType(),
                Keyword.symVar(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void type_ass() throws IOException, OberonException {
        Logger.debug("Start: type_ass");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");
            
            Identifier id = match(new Identifier(), Identifier.class);

            match(Operator.eq(), Operator.class);

            Type type = type().val;

            Declaration decl = new Declaration(id, new TypedefType(type), null);
            try {
                env.putDecl(id.getLexeme(), decl);
            } catch (OberonException e) {
                Declaration prev = env.getDecl(id.getLexeme());
                throw new IdentifierAlreadyExists(id, prev.getId());
            }

            match(Symbol.semicol(), Symbol.class);

            type_ass();

            Logger.debug("End: type_ass");
        }
        else if (
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: type_ass");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                Keyword.symVar(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private _r_type type() throws IOException, OberonException {
        Logger.debug("Start: type");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);

            Declaration decl = env.getDecl(id.getLexeme());
            if (decl == null) {
                throw new UnknownIdentifier(id);
            }
            Type type = decl.getType();
            if (!(type instanceof TypedefType)) {
                throw new UnexpectedType("TypedefType", type, id.getLine(), id.getColumn());
            }

            Logger.debug("End: type");
            return new _r_type(((TypedefType)type).getType());
        }
        else if (lookahead.equals(Keyword.typeArray())) {
            Logger.debug("Use: eq2");

            Type type = type_arr().val;

            Logger.debug("End: type");
            return new _r_type(type);
        }
        else if (lookahead.equals(Keyword.typeRecord())) {
            Logger.debug("Use: eq3");

            Type type = type_rec().val;

            Logger.debug("End: type");
            return new _r_type(type);
        }
        else if (lookahead.equals(Keyword.typeInt())) {
            Logger.debug("Use: eq4");

            match(Keyword.typeInt(), Keyword.class);

            Logger.debug("End: type");
            return new _r_type(new IntegerType());
        }
        else if (lookahead.equals(Keyword.typeBool())) {
            Logger.debug("Use: eq5");

            match(Keyword.typeBool(), Keyword.class);

            Logger.debug("End: type");
            return new _r_type(new BooleanType());
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                Keyword.typeArray(),
                Keyword.typeRecord(),
                Keyword.typeInt(),
                Keyword.typeBool()
            }, lookahead);
        }
    }

    private _r_type_arr type_arr() throws IOException, OberonException {
        Logger.debug("Start: type_arr");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.typeArray())) {
            Logger.debug("Use: eq1");

            match(Keyword.typeArray(), Keyword.class);
            
            Token tmp = lookahead;
            Type exprType = expr().type;
            if (!(exprType instanceof IntegerType)) {
                throw new UnexpectedType(new IntegerType(), exprType, tmp.getLine(), tmp.getColumn());
            }

            match(Keyword.symOf(), Keyword.class);

            Type elementType = type().val;

            Logger.debug("End: type_arr");
            return new _r_type_arr(new ArrayType(elementType));
        }
        else {
            throw new UnexpectedToken(Keyword.typeArray(), lookahead);
        }
    }

    private _r_type_rec type_rec() throws IOException, OberonException {
        Logger.debug("Start: type_rec");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.typeRecord())) {
            Logger.debug("Use: eq1");

            match(Keyword.typeRecord(), Keyword.class);

            Hashtable<String, Declaration> fields = field_list(new Hashtable<String, Declaration>()).fields;

            match(Keyword.symEnd(), Keyword.class);

            Logger.debug("End: type_rec");
            return new _r_type_rec(new RecordType(fields));
        }
        else {
            throw new UnexpectedToken(Keyword.typeRecord(), lookahead);
        }
    }

    private _r_field_list field_list(Hashtable<String, Declaration> l_fields) throws IOException, OberonException {
        Logger.debug("Start: field_list");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);

            match(Symbol.colon(), Symbol.class);

            Type type = type().val;

            match(Symbol.semicol(), Symbol.class);

            Hashtable<String, Object> extAttrs = new Hashtable<String, Object>();
            extAttrs.put("modifiers", new Declaration.Modifiers[] { Declaration.Modifiers.VAR });
            Declaration decl = new Declaration(id, type, extAttrs);
            if (l_fields.containsKey(id.getLexeme())) {
                Declaration prevDecl = l_fields.get(id.getLexeme());
                throw new IdentifierAlreadyExists(id, prevDecl.getId());
            }
            l_fields.put(id.getLexeme(), decl);

            Hashtable<String, Declaration> fields = field_list(l_fields).fields;

            Logger.debug("End: field_list");
            return new _r_field_list(fields);
        }
        else if (lookahead.equals(Keyword.symEnd())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: field_list");
            return new _r_field_list(l_fields);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void var_decl() throws IOException, OberonException {
        Logger.debug("Start: var_decl");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symVar())) {
            Logger.debug("Use: eq1");

            match(Keyword.symVar(), Keyword.class);

            var_list();

            Logger.debug("End: var_decl");
        }
        else if (
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: var_decl");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symVar(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void var_list() throws IOException, OberonException {
        Logger.debug("Start: var_list");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            ArrayList<Identifier> ids = id_list().ids;

            match(Symbol.colon(), Symbol.class);
            
            Type type = type().val;

            match(Symbol.semicol(), Symbol.class);

            for (Identifier id : ids) {
                Hashtable<String, Object> extAttrs = new Hashtable<String, Object>();
                extAttrs.put("modifiers", new Declaration.Modifiers[] { Declaration.Modifiers.VAR });
                Declaration decl = new Declaration(id, type, extAttrs);
                try {
                    env.putDecl(id.getLexeme(), decl);
                } catch (OberonException e)  {
                    Declaration prevDecl = env.getDecl(id.getLexeme());
                    throw new IdentifierAlreadyExists(id, prevDecl.getId());
                }
            }

            var_list();

            Logger.debug("End: var_list");
        }
        else if (
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: var_list");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void proc_decl_seq(flowchart.Module g_mod) throws IOException, OberonException {
        Logger.debug("Start: proc_decl_seq");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symProcedure())) {
            Logger.debug("Use: eq1");

            proc_decl(g_mod);

            match(Symbol.semicol(), Symbol.class);

            proc_decl_seq(g_mod);

            Logger.debug("End: proc_decl_seq");
        }
        else if (
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: proc_decl_seq");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symProcedure(),
                Keyword.symBegin(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void proc_decl(flowchart.Module g_mod) throws IOException, OberonException {
        Logger.debug("Start: proc_decl");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symProcedure())) {
            Logger.debug("Use: eq1");

            Env cur = env;
            env = new Env(env);

            _r_proc_head head = proc_head();

            match(Symbol.semicol(), Symbol.class);

            flowchart.Procedure g_proc = g_mod.add(head.id.getLexeme());

            proc_body(g_mod, g_proc, head.id);

            env = cur;
            try {
                env.putDecl(head.id.getLexeme(), head.decl);
            } catch (OberonException e)  {
                Declaration prevDecl = env.getDecl(head.id.getLexeme());
                throw new IdentifierAlreadyExists(head.id, prevDecl.getId());
            }

            Logger.debug("End: proc_decl");
        }
        else {
            throw new UnexpectedToken(Keyword.symProcedure(), lookahead);
        }
    }

    private _r_proc_head proc_head() throws IOException, OberonException {
        Logger.debug("Start: proc_head");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symProcedure())) {
            Logger.debug("Use: eq1");

            match(Keyword.symProcedure(), Keyword.class);

            Identifier id = match(new Identifier(), Identifier.class);

            _r_proc_pars pars = proc_pars();

            Declaration decl = new Declaration(id, new ProcedureType(pars.params), null);

            Logger.debug("End: proc_head");
            return new _r_proc_head(id, decl);
        }
        else {
            throw new UnexpectedToken(Keyword.symProcedure(), lookahead);
        }
    }

    private _r_proc_pars proc_pars() throws IOException, OberonException {
        Logger.debug("Start: proc_pars");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.lpar())) {
            Logger.debug("Use: eq1");

            match(Symbol.lpar(), Symbol.class);
            
            _r_par_list pars = par_list();

            match(Symbol.rpar(), Symbol.class);

            Logger.debug("End: proc_pars");
            return new _r_proc_pars(pars.params);
        }
        else if (lookahead.equals(Symbol.semicol())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: proc_pars");
            return new _r_proc_pars(new ArrayList<Declaration>());
        }
        else {
            throw new UnexpectedToken(new Token[] { Symbol.lpar(), Symbol.semicol() }, lookahead);
        }
    }

    private _r_par_list par_list() throws IOException, OberonException {
        Logger.debug("Start: par_list");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(Keyword.symVar())
        ) {
            Logger.debug("Use: eq1");

            _r_par pars = par();

            _r_ext_par extPars = ext_par();

            ArrayList<Declaration> params = new ArrayList<Declaration>();
            params.addAll(pars.params);
            params.addAll(extPars.params);

            Logger.debug("End: par_list");
            return new _r_par_list(params);
        }
        else {
            throw new UnexpectedToken(new Token[] { new Identifier(), Keyword.symVar() }, lookahead);
        }
    }

    private _r_par par() throws IOException, OberonException {
        Logger.debug("Start: par");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symVar())) {
            Logger.debug("Use: eq1");

            ArrayList<Declaration> params = new ArrayList<Declaration>();

            match(Keyword.symVar(), Keyword.class);

            ArrayList<Identifier> ids = id_list().ids;

            match(Symbol.colon(), Symbol.class);

            Type type = type().val;

            for (Identifier id : ids) {
                Hashtable<String, Object> extAttrs = new Hashtable<String, Object>();
                extAttrs.put("modifiers", new Declaration.Modifiers[] { Declaration.Modifiers.VAR });
                Declaration decl = new Declaration(id, type, extAttrs);
                params.add(decl);

                try {
                    env.putDecl(id.getLexeme(), decl);
                } catch (OberonException e) {
                    Declaration prev = env.getDecl(id.getLexeme());
                    throw new IdentifierAlreadyExists(id, prev.getId());
                }
            }

            Logger.debug("End: par");
            return new _r_par(params);
        } else if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq2");

            ArrayList<Declaration> params = new ArrayList<Declaration>();

            ArrayList<Identifier> ids = id_list().ids;

            match(Symbol.colon(), Symbol.class);

            Type type = type().val;

            for (Identifier id : ids) {
                Declaration decl = new Declaration(id, type, null);
                params.add(decl);

                try {
                    env.putDecl(id.getLexeme(), decl);
                } catch (OberonException e) {
                    Declaration prev = env.getDecl(id.getLexeme());
                    throw new IdentifierAlreadyExists(id, prev.getId());
                }
            }

            Logger.debug("End: par");
            return new _r_par(params);
        }
        else {
            throw new UnexpectedToken(new Token[] { Keyword.symVar(), new Identifier() }, lookahead);
        }
    }

    private _r_ext_par ext_par() throws IOException, OberonException {
        Logger.debug("Start: ext_par");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.semicol())) {
            Logger.debug("Use: eq1");

            match(Symbol.semicol(), Symbol.class);

            _r_par pars = par();

            _r_ext_par extPars = ext_par();

            ArrayList<Declaration> params = new ArrayList<Declaration>();
            params.addAll(pars.params);
            params.addAll(extPars.params);

            Logger.debug("End: ext_par");
            return new _r_ext_par(params);
        }
        else if (lookahead.equals(Symbol.rpar())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: ext_par");
            return new _r_ext_par(new ArrayList<Declaration>());
        }
        else {
            throw new UnexpectedToken(new Token[] { Symbol.semicol(), Symbol.rpar() }, lookahead);
        }
    }

    private _r_id_list id_list() throws IOException, OberonException {
        Logger.debug("Start: id_list");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);

            _r_ext_id extIds = ext_id();

            ArrayList<Identifier> ids = new ArrayList<Identifier>();
            ids.add(id);
            ids.addAll(extIds.ids);

            Logger.debug("End: id_list");
            return new _r_id_list(ids);
        }
        else {
            throw new UnexpectedToken(new Token[] { new Identifier() }, lookahead);
        }
    }

    private _r_ext_id ext_id() throws IOException, OberonException {
        Logger.debug("Start: ext_id");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.comma())) {
            Logger.debug("Use: eq1");

            match(Symbol.comma(), Symbol.class);

            Identifier id = match(new Identifier(), Identifier.class);

            _r_ext_id extIds = ext_id();

            ArrayList<Identifier> ids = new ArrayList<Identifier>();
            ids.add(id);
            ids.addAll(extIds.ids);

            Logger.debug("End: ext_id");
            return new _r_ext_id(ids);
        }
        else if (lookahead.equals(Symbol.colon())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: ext_id");
            return new _r_ext_id(new ArrayList<Identifier>());
        }
        else {
            throw new UnexpectedToken(new Token[] { Symbol.comma(), Symbol.colon() }, lookahead);
        }
    }

    private void proc_body(flowchart.Module g_mod, flowchart.Procedure g_proc, Identifier decl_id) throws IOException, OberonException {
        Logger.debug("Start: proc_body");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Keyword.symConst()) ||
            lookahead.equals(Keyword.symType()) ||
            lookahead.equals(Keyword.symVar()) ||
            lookahead.equals(Keyword.symProcedure()) ||
            lookahead.equals(Keyword.symBegin()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            Logger.debug("Use: eq1");

            decls(g_mod);

            stat_block(g_proc::add);

            match(Keyword.symEnd(), Keyword.class);

            Identifier id = match(new Identifier(), Identifier.class);
            if (!decl_id.getLexeme().toLowerCase().equals(id.getLexeme().toLowerCase())) {
                throw new MismatchedDeclaration("PROCEDURE", decl_id, id);
            }

            Logger.debug("End: proc_body");
        }
        else {
            throw new UnexpectedToken(new Token[] { Keyword.symConst(), Keyword.symType(), Keyword.symVar(), Keyword.symProcedure(), Keyword.symBegin(), Keyword.symEnd() }, lookahead);
        }
    }

    private void stat_seq(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: stat_seq");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(Keyword.symIf()) ||
            lookahead.equals(Keyword.symWhile())
        ) {
            Logger.debug("Use: eq1");

            stat(g_stats);

            ext_stat(g_stats);

            Logger.debug("End: stat_seq");
        }
        else {
            throw new UnexpectedToken(new Token[] { new Identifier(), Keyword.symIf(), Keyword.symWhile() }, lookahead);
        }
    }

    private void ext_stat(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: ext_stat");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.semicol())) {
            Logger.debug("Use: eq1");

            match(Symbol.semicol(), Symbol.class);

            stat(g_stats);

            ext_stat(g_stats);

            Logger.debug("End: ext_stat");
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: ext_stat");
        }
        else {
            throw new UnexpectedToken(new Token[] { Symbol.semicol(), Keyword.symEnd(), Keyword.symElif(), Keyword.symElse() }, lookahead);
        }
    }

    private void stat(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: stat");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);

            Declaration decl = env.getDecl(id.getLexeme());
            if (decl == null) {
                throw new UnknownIdentifier(id);
            }

            id_stat(id, decl, g_stats);

            Logger.debug("End: stat");
        }
        else if (lookahead.equals(Keyword.symIf())) {
            Logger.debug("Use: eq2");

            flowchart.IfStatement stmt = if_stat().statement;

            g_stats.add(stmt);

            Logger.debug("End: stat");
        }
        else if (lookahead.equals(Keyword.symWhile())) {
            Logger.debug("Use: eq3");

            flowchart.WhileStatement stmt = while_stat().statement;

            g_stats.add(stmt);

            Logger.debug("End: stat");
        }
        else {
            throw new UnexpectedToken(new Token[] { new Identifier(), Keyword.symIf(), Keyword.symWhile() }, lookahead);
        }
    }

    private void id_stat(Identifier id, Declaration decl, SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: id_stat");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Symbol.lbrack()) ||
            lookahead.equals(Symbol.dot()) ||
            lookahead.equals(Operator.assign())
        ) {
            Logger.debug("Use: eq1");

            flowchart.PrimitiveStatement stmt = assignment(id, decl).statement;

            g_stats.add(stmt);

            Logger.debug("End: id_stat");
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Symbol.semicol())
        ) {
            Logger.debug("Use: eq2");

            flowchart.PrimitiveStatement stmt = proc_call(decl).statement;

            g_stats.add(stmt);

            Logger.debug("End: id_stat");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Symbol.lbrack(),
                Symbol.dot(),
                Operator.assign(),
                Keyword.symEnd(),
                Keyword.symElif(),
                Keyword.symElse(),
                Symbol.lpar(),
                Symbol.semicol()
            }, lookahead);
        }
    }

    private _r_assignment assignment(Identifier id, Declaration decl) throws IOException, OberonException {
        Logger.debug("Start: assignment");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Symbol.lbrack()) ||
            lookahead.equals(Symbol.dot()) ||
            lookahead.equals(Operator.assign())
        ) {
            Logger.debug("Use: eq1");

            Object modifiers = decl.getExtAttr("modifiers");
            if (modifiers instanceof Declaration.Modifiers[]) {
                Declaration.Modifiers[] mods = (Declaration.Modifiers[]) modifiers;
                boolean isVar = false;
                for (Declaration.Modifiers mod : mods) {
                    if (mod == Declaration.Modifiers.VAR) {
                        isVar = true;
                        break;
                    }
                }
                if (!isVar) {
                    throw new VariableNotAssignable(decl, id);
                }
            } else {
                throw new VariableNotAssignable(decl, id);
            }

            _r_sel select = sel(decl.getType());

            Operator op = match(Operator.assign(), Operator.class);

            _r_expr expression = expr();

            if (!select.type.equals(expression.type))  {
                throw new TypeMismatch(select.type, expression.type, op);
            }

            String text = decl.getLexeme() + select.text + ":=" + expression.text;
            flowchart.PrimitiveStatement stmt = new flowchart.PrimitiveStatement(text);
            
            Logger.debug("End: assignment");
            return new _r_assignment(stmt);
        }
        else {
            throw new UnexpectedToken(new Token[] { Symbol.lbrack(), Symbol.dot(), Operator.assign() }, lookahead);
        }
    }

    private _r_sel sel(Type parent_type) throws IOException, OberonException {
        Logger.debug("Start: sel");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.dot())) {
            Logger.debug("Use: eq1");

            if (!(parent_type instanceof RecordType)) {
                throw new UnexpectedType("RecordType", parent_type, lookahead.getLine(), lookahead.getColumn());
            }

            match(Symbol.dot(), Symbol.class);

            Identifier id = match(new Identifier(), Identifier.class);

            Hashtable<String, Declaration> fields = ((RecordType) parent_type).getFields();
            if (!fields.containsKey(id.getLexeme())) {
                throw new UnknownIdentifier(id);
            }
            Type childType = fields.get(id.getLexeme()).getType();

            _r_sel select = sel(childType);

            Logger.debug("End: sel");
            return new _r_sel("." + id.getLexeme() + select.text, select.type);
        }
        else if (lookahead.equals(Symbol.lbrack())) {
            Logger.debug("Use: eq2");

            if (!(parent_type instanceof ArrayType)) {
                throw new UnexpectedType("ArrayType", parent_type, lookahead.getLine(), lookahead.getColumn());
            }

            match(Symbol.lbrack(), Symbol.class);

            Token tmp = lookahead;
            _r_expr expression = expr();

            if (!(expression.type instanceof IntegerType)) {
                throw new UnexpectedType(new IntegerType(), expression.type, tmp.getLine(), tmp.getColumn());
            }

            match(Symbol.rbrack(), Symbol.class);

            Type childType = ((ArrayType) parent_type).getElementType();

            _r_sel select = sel(childType);

            Logger.debug("End: sel");
            return new _r_sel("[" + expression.text + "]" + select.text, select.type);
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symOf()) ||
            lookahead.equals(Keyword.symThen()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Keyword.symDo()) ||
            lookahead.equals(Operator.or()) ||
            lookahead.equals(Operator.div()) ||
            lookahead.equals(Operator.mod()) ||
            lookahead.equals(Symbol.rpar()) ||
            lookahead.equals(Symbol.rbrack()) ||
            lookahead.equals(Operator.eq()) ||
            lookahead.equals(Operator.neq()) ||
            lookahead.equals(Operator.lt()) ||
            lookahead.equals(Operator.lteq()) ||
            lookahead.equals(Operator.gt()) ||
            lookahead.equals(Operator.gteq()) ||
            lookahead.equals(Operator.plus()) ||
            lookahead.equals(Operator.minus()) ||
            lookahead.equals(Operator.mult()) ||
            lookahead.equals(Operator.and()) ||
            lookahead.equals(Symbol.semicol()) ||
            lookahead.equals(Symbol.comma()) ||
            lookahead.equals(Operator.assign())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq3)");
            Logger.debug("End: sel");
            return new _r_sel("", parent_type);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Symbol.dot(),
                Symbol.lbrack(),
                Keyword.symEnd(),
                Keyword.symOf(),
                Keyword.symThen(),
                Keyword.symElif(),
                Keyword.symElse(),
                Keyword.symDo(),
                Operator.or(),
                Operator.div(),
                Operator.mod(),
                Symbol.rpar(),
                Symbol.rbrack(),
                Operator.eq(),
                Operator.neq(),
                Operator.lt(),
                Operator.lteq(),
                Operator.gt(),
                Operator.gteq(),
                Operator.plus(),
                Operator.minus(),
                Operator.mult(),
                Operator.and(),
                Symbol.semicol(),
                Symbol.comma(),
                Operator.assign()
            }, lookahead);
        }
    }

    private _r_proc_call proc_call(Declaration decl) throws IOException, OberonException {
        Logger.debug("Start: proc_call");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Symbol.semicol())
        ) {
            Logger.debug("Use: eq1");

            if (!(decl.getType() instanceof ProcedureType)) {
                throw new UnexpectedType("ProcedureType", decl.getType(), lookahead.getLine(), lookahead.getColumn());
            }

            _r_act_pars act_pars = act_pars();

            if (!(ProcedureType.rawSig(act_pars.types).equals((ProcedureType) decl.getType()))) {
                throw new TypeMismatch(decl.getType(), ProcedureType.rawSig(act_pars.types), lookahead.getLine(), lookahead.getColumn());
            }

            String text = decl.getLexeme() + act_pars.text;
            flowchart.PrimitiveStatement stmt = new flowchart.PrimitiveStatement(text);

            Logger.debug("End: proc_call");
            return new _r_proc_call(stmt);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symEnd(),
                Keyword.symElif(),
                Keyword.symElse(),
                Symbol.lpar(),
                Symbol.semicol()
            }, lookahead);
        }
    }

    private _r_act_pars act_pars() throws IOException, OberonException {
        Logger.debug("Start: act_pars");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.lpar())) {
            Logger.debug("Use: eq1");

            match(Symbol.lpar(), Symbol.class);

            _r_expr_list expr_list = expr_list();

            match(Symbol.rpar(), Symbol.class);

            Logger.debug("End: act_pars");
            return new _r_act_pars(expr_list.types, "(" + expr_list.text + ")");
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Symbol.semicol())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: act_pars");
            return new _r_act_pars(new ArrayList<Type>(), "");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Symbol.lpar(),
                Keyword.symEnd(),
                Keyword.symElif(),
                Keyword.symElse(),
                Symbol.semicol()
            }, lookahead);
        }
    }

    private _r_expr_list expr_list() throws IOException, OberonException {
        Logger.debug("Start: expr_list");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(new Number()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Operator.not()) ||
            lookahead.equals(Operator.plus()) ||
            lookahead.equals(Operator.minus())
        ) {
            Logger.debug("Use: eq1");

            _r_expr expression = expr();

            _r_ext_expr extExpression = ext_expr();

            ArrayList<Type> types = new ArrayList<Type>();
            types.add(expression.type);
            types.addAll(extExpression.types);

            Logger.debug("End: expr_list");
            return new _r_expr_list(types, expression.text + extExpression.text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                new Number(),
                Symbol.lpar(),
                Operator.not(),
                Operator.plus(),
                Operator.minus()
            }, lookahead);
        }
    }

    private _r_ext_expr ext_expr() throws IOException, OberonException {
        Logger.debug("Start: ext_expr");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Symbol.comma())) {
            Logger.debug("Use: eq1");

            match(Symbol.comma(), Symbol.class);

            _r_expr expression = expr();

            _r_ext_expr extExpression = ext_expr();

            ArrayList<Type> types = new ArrayList<Type>();
            types.add(expression.type);
            types.addAll(extExpression.types);

            Logger.debug("End: ext_expr");
            return new _r_ext_expr(types, "," + expression.text + extExpression.text);
        }
        else if (lookahead.equals(Symbol.rpar())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: ext_expr");
            return new _r_ext_expr(new ArrayList<Type>(), "");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Symbol.comma(),
                Symbol.rpar()
            }, lookahead);
        }
    }

    private _r_if_stat if_stat() throws IOException, OberonException {
        Logger.debug("Start: if_stat");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symIf())) {
            Logger.debug("Use: eq1");

            match(Keyword.symIf(), Keyword.class);

            Token tmp = lookahead;
            _r_expr expression = expr();

            if (!(expression.type instanceof BooleanType)) {
                throw new UnexpectedType(new BooleanType(), expression.type, tmp.getLine(), tmp.getColumn());
            }
            flowchart.IfStatement ifStmt = new flowchart.IfStatement(expression.text);

            match(Keyword.symThen(), Keyword.class);

            stat_seq(ifStmt.getTrueBody()::add);

            _r_elif_seq elif = elif_seq(ifStmt.getFalseBody()::add);

            SeqHandle g_stats = elif.g_pstats;

            else_stat(g_stats);

            match(Keyword.symEnd(), Keyword.class);

            Logger.debug("End: if_stat");
            return new _r_if_stat(ifStmt);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symIf()
            }, lookahead);
        }
    }

    private _r_elif_seq elif_seq(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: elif_seq");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symElif())) {
            Logger.debug("Use: eq1");

            match(Keyword.symElif(), Keyword.class);

            Token tmp = lookahead;
            _r_expr expression = expr();

            if (!(expression.type instanceof BooleanType)) {
                throw new UnexpectedType(new BooleanType(), expression.type, tmp.getLine(), tmp.getColumn());
            }
            flowchart.IfStatement elifStmt = new flowchart.IfStatement(expression.text);

            match(Keyword.symThen(), Keyword.class);

            stat_seq(elifStmt.getTrueBody()::add);

            _r_elif_seq elif = elif_seq(elifStmt.getFalseBody()::add);

            g_stats.add(elifStmt);

            Logger.debug("End: elif_seq");
            return new _r_elif_seq(elif.g_pstats);
        }
        else if (
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Keyword.symEnd())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: elif_seq");
            return new _r_elif_seq(g_stats);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symElif(),
                Keyword.symElse(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private void else_stat(SeqHandle g_stats) throws IOException, OberonException {
        Logger.debug("Start: else_stat");
        Logger.debug("Lookahead: " + lookahead.toString());
        
        if (lookahead.equals(Keyword.symElse())) {
            Logger.debug("Use: eq1");

            match(Keyword.symElse(), Keyword.class);

            stat_seq(g_stats);
            
            Logger.debug("End: else_stat");
        }
        else if (lookahead.equals(Keyword.symEnd())) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: else_stat");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symElse(),
                Keyword.symEnd()
            }, lookahead);
        }
    }

    private _r_while_stat while_stat() throws IOException, OberonException {
        Logger.debug("Start: while_stat");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Keyword.symWhile()))  {
            Logger.debug("Use: eq1");

            match(Keyword.symWhile(), Keyword.class);

            Token tmp = lookahead;
            _r_expr expression = expr();

            if (!(expression.type instanceof BooleanType)) {
                throw new UnexpectedType(new BooleanType(), expression.type, tmp.getLine(), tmp.getColumn());
            }
            flowchart.WhileStatement whileStmt = new flowchart.WhileStatement(expression.text);

            match(Keyword.symDo(), Keyword.class);

            stat_seq(whileStmt.getLoopBody()::add);

            match(Keyword.symEnd(), Keyword.class);

            Logger.debug("End: while_stat");
            return new _r_while_stat(whileStmt);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Keyword.symWhile()
            }, lookahead);
        }
    }

    private _r_expr expr() throws IOException, OberonException {
        Logger.debug("Start: expr");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(new Number()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Operator.not()) ||
            lookahead.equals(Operator.plus()) ||
            lookahead.equals(Operator.minus())
        ) {
            Logger.debug("Use: eq1");

            _r_simple_expr simpleExpr = simple_expr();

            _r_post_expr postExpr = post_expr(simpleExpr.type, simpleExpr.text);

            Logger.debug("End: expr");
            return new _r_expr(postExpr.type, postExpr.text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                new Number(),
                Symbol.lpar(),
                Operator.not(),
                Operator.plus(),
                Operator.minus()
            }, lookahead);
        }
    }

    private _r_post_expr post_expr(Type l_type, String l_text) throws IOException, OberonException {
        Logger.debug("Start: post_expr");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(Operator.eq()) ||
            lookahead.equals(Operator.neq()) ||
            lookahead.equals(Operator.lt()) ||
            lookahead.equals(Operator.lteq()) ||
            lookahead.equals(Operator.gt()) ||
            lookahead.equals(Operator.gteq())
        ) {
            Logger.debug("Use: eq1");

            _r_rel_op relOp = rel_op();

            _r_simple_expr simpleExpr = simple_expr();

            if (!(relOp.op.equals(Operator.eq()) || relOp.op.equals(Operator.neq()))) {
                if (!(l_type instanceof IntegerType) || !(simpleExpr.type instanceof IntegerType)) {
                    throw new UnexpectedType(relOp.op, new IntegerType(), new IntegerType(), l_type, simpleExpr.type);
                }
            }
            else {
                if (!(l_type.equals(simpleExpr.type))) {
                    throw new TypeMismatch(l_type, simpleExpr.type, relOp.op.getLine(), relOp.op.getColumn());
                }
            }

            Logger.debug("End: post_expr");
            return new _r_post_expr(new BooleanType(), l_text + relOp.text + simpleExpr.text);
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symOf()) ||
            lookahead.equals(Keyword.symThen()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Keyword.symDo()) ||
            lookahead.equals(Symbol.rpar()) ||
            lookahead.equals(Symbol.rbrack()) ||
            lookahead.equals(Symbol.semicol()) ||
            lookahead.equals(Symbol.comma())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq2)");
            Logger.debug("End: post_expr");
            return new _r_post_expr(l_type, l_text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Operator.eq(),
                Operator.neq(),
                Operator.lt(),
                Operator.lteq(),
                Operator.gt(),
                Operator.gteq(),
                Keyword.symEnd(),
                Keyword.symOf(),
                Keyword.symThen(),
                Keyword.symElif(),
                Keyword.symElse(),
                Keyword.symDo(),
                Symbol.rpar(),
                Symbol.rbrack(),
                Symbol.semicol(),
                Symbol.comma()
            }, lookahead);
        }
    }

    private _r_rel_op rel_op() throws IOException, OberonException {
        Logger.debug("Start: rel_op");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Operator.eq())) {
            Logger.debug("Use: eq1");
            Logger.debug("End: rel_op");
            return new _r_rel_op("=", match(Operator.eq(), Operator.class));
        }
        else if (lookahead.equals(Operator.neq())) {
            Logger.debug("Use: eq2");
            Logger.debug("End: rel_op");
            return new _r_rel_op("#", match(Operator.neq(), Operator.class));
        }
        else if (lookahead.equals(Operator.lt())) {
            Logger.debug("Use: eq3");
            Logger.debug("End: rel_op");
            return new _r_rel_op("&lt;", match(Operator.lt(), Operator.class));
        }
        else if (lookahead.equals(Operator.lteq())) {
            Logger.debug("Use: eq4");
            Logger.debug("End: rel_op");
            return new _r_rel_op("&lt;=", match(Operator.lteq(), Operator.class));
        }
        else if (lookahead.equals(Operator.gt())) {
            Logger.debug("Use: eq5");
            Logger.debug("End: rel_op");
            return new _r_rel_op("&gt;", match(Operator.gt(), Operator.class));
        }
        else if (lookahead.equals(Operator.gteq())) {
            Logger.debug("Use: eq6");
            Logger.debug("End: rel_op");
            return new _r_rel_op("&gt;=", match(Operator.gteq(), Operator.class));
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Operator.eq(),
                Operator.neq(),
                Operator.lt(),
                Operator.lteq(),
                Operator.gt(),
                Operator.gteq()
            }, lookahead);
        }
    }

    private _r_simple_expr simple_expr() throws IOException, OberonException {
        Logger.debug("Start: simple_expr");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Operator.plus())) {
            Logger.debug("Use: eq1");
            
            match(Operator.plus(), Operator.class);

            Token tmp = lookahead;
            _r_term t = term();

            if (!(t.type instanceof IntegerType)) {
                throw new UnexpectedType(new IntegerType(), t.type, tmp.getLine(), tmp.getColumn());
            }
            Type l_type = new IntegerType();
            String l_text = "(+" + t.text + ")";

            _r_post_term postTerm = post_term(l_type, l_text);

            Logger.debug("End: simple_expr");
            return new _r_simple_expr(postTerm.type, postTerm.text);
        }
        else if (lookahead.equals(Operator.minus())) {
            Logger.debug("Use: eq2");

            match(Operator.minus(), Operator.class);

            Token tmp = lookahead;
            _r_term t = term();

            if (!(t.type instanceof IntegerType)) {
                throw new UnexpectedType(new IntegerType(), t.type, tmp.getLine(), tmp.getColumn());
            }
            Type l_type = new IntegerType();
            String l_text = "(-" + t.text + ")";

            _r_post_term postTerm = post_term(l_type, l_text);

            Logger.debug("End: simple_expr");
            return new _r_simple_expr(postTerm.type, postTerm.text);
        }
        else if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(new Number()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Operator.not())
        ) {
            Logger.debug("Use: eq3");

            _r_term t = term();

            Type l_type = t.type;
            String l_text = t.text;

            _r_post_term postTerm = post_term(l_type, l_text);

            Logger.debug("End: simple_expr");
            return new _r_simple_expr(postTerm.type, postTerm.text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Operator.plus(),
                Operator.minus(),
                new Identifier(),
                new Number(),
                Symbol.lpar(),
                Operator.not()
            }, lookahead);
        }
    }

    private _r_post_term post_term(Type l_type, String l_text) throws IOException, OberonException {
        Logger.debug("Start: post_term");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Operator.plus())) {
            Logger.debug("Use: eq1");

            Operator op = match(Operator.plus(), Operator.class);

            _r_term t = term();

            if (!(l_type instanceof IntegerType) || !(t.type instanceof IntegerType)) {
                throw new UnexpectedType(op, new IntegerType(), new IntegerType(), l_type, t.type);
            }

            Type post_l_type = new IntegerType();
            String post_l_text = l_text + "+" + t.text;

            _r_post_term postTerm = post_term(post_l_type, post_l_text);

            Logger.debug("End: post_term");
            return new _r_post_term(postTerm.type, postTerm.text);
        }
        else if (lookahead.equals(Operator.minus())) {
            Logger.debug("Use: eq2");

            Operator op = match(Operator.minus(), Operator.class);

            _r_term t = term();

            if (!(l_type instanceof IntegerType) || !(t.type instanceof IntegerType)) {
                throw new UnexpectedType(op, new IntegerType(), new IntegerType(), l_type, t.type);
            }
            
            Type post_l_type = new IntegerType();
            String post_l_text = l_text + "-" + t.text;

            _r_post_term postTerm = post_term(post_l_type, post_l_text);

            Logger.debug("End: post_term");
            return new _r_post_term(postTerm.type, postTerm.text);
        }
        else if (lookahead.equals(Operator.or())) {
            Logger.debug("Use: eq3");

            Operator op = match(Operator.or(), Operator.class);

            _r_term t = term();

            if (!(l_type instanceof BooleanType) || !(t.type instanceof BooleanType)) {
                throw new UnexpectedType(op, new BooleanType(), new BooleanType(), l_type, t.type);
            }

            Type post_l_type = new BooleanType();
            String post_l_text = l_text + " OR " + t.text;

            _r_post_term postTerm = post_term(post_l_type, post_l_text);

            Logger.debug("End: post_term");
            return new _r_post_term(postTerm.type, postTerm.text);
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symOf()) ||
            lookahead.equals(Keyword.symThen()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Keyword.symDo()) ||
            lookahead.equals(Symbol.rpar()) ||
            lookahead.equals(Symbol.rbrack()) ||
            lookahead.equals(Operator.eq()) ||
            lookahead.equals(Operator.neq()) ||
            lookahead.equals(Operator.lt()) ||
            lookahead.equals(Operator.lteq()) ||
            lookahead.equals(Operator.gt()) ||
            lookahead.equals(Operator.gteq()) ||
            lookahead.equals(Symbol.semicol()) ||
            lookahead.equals(Symbol.comma())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq4)");
            Logger.debug("End: post_term");
            return new _r_post_term(l_type, l_text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Operator.plus(),
                Operator.minus(),
                Operator.or(),
                Keyword.symEnd(),
                Keyword.symOf(),
                Keyword.symThen(),
                Keyword.symElif(),
                Keyword.symElse(),
                Keyword.symDo(),
                Symbol.rpar(),
                Symbol.rbrack(),
                Operator.eq(),
                Operator.neq(),
                Operator.lt(),
                Operator.lteq(),
                Operator.gt(),
                Operator.gteq(),
                Symbol.semicol(),
                Symbol.comma()
            }, lookahead);
        }
    }

    private _r_term term() throws IOException, OberonException {
        Logger.debug("Start: term");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (
            lookahead.equals(new Identifier()) ||
            lookahead.equals(new Number()) ||
            lookahead.equals(Symbol.lpar()) ||
            lookahead.equals(Operator.not())
        ) {
            Logger.debug("Use: eq1");

            _r_factor f = factor();

            _r_post_factor postFactor = post_factor(f.type, f.text);

            Logger.debug("End: term");
            return new _r_term(postFactor.type, postFactor.text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                new Number(),
                Symbol.lpar(),
                Operator.not()
            }, lookahead);
        }
    }

    private _r_post_factor post_factor(Type l_type, String l_text) throws IOException, OberonException {
        Logger.debug("Start: post_factor");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(Operator.mult())) {
            Logger.debug("Use: eq1");

            Operator op = match(Operator.mult(), Operator.class);

            _r_factor f = factor();

            if (!(l_type instanceof IntegerType) || !(f.type instanceof IntegerType)) {
                throw new UnexpectedType(op, new IntegerType(), new IntegerType(), l_type, f.type);
            }

            Type post_l_type = new IntegerType();
            String post_l_text = l_text + "*" + f.text;

            _r_post_factor postFactor = post_factor(post_l_type, post_l_text);

            Logger.debug("End: post_factor");
            return new _r_post_factor(postFactor.type, postFactor.text);
        }
        else if (lookahead.equals(Operator.and())) {
            Logger.debug("Use: eq2");

            Operator op = match(Operator.and(), Operator.class);

            _r_factor f = factor();

            if (!(l_type instanceof BooleanType) || !(f.type instanceof BooleanType)) {
                throw new UnexpectedType(op, new BooleanType(), new BooleanType(), l_type, f.type);
            }

            Type post_l_type = new BooleanType();
            String post_l_text = l_text + "&" + f.text;

            _r_post_factor postFactor = post_factor(post_l_type, post_l_text);

            Logger.debug("End: post_factor");
            return new _r_post_factor(postFactor.type, postFactor.text);
        }
        else if (lookahead.equals(Operator.div())) {
            Logger.debug("Use: eq3");

            Operator op = match(Operator.div(), Operator.class);

            _r_factor f = factor();

            if (!(l_type instanceof IntegerType) || !(f.type instanceof IntegerType)) {
                throw new UnexpectedType(op, new IntegerType(), new IntegerType(), l_type, f.type);
            }

            Type post_l_type = new IntegerType();
            String post_l_text = l_text + "~/" + f.text;

            _r_post_factor postFactor = post_factor(post_l_type, post_l_text);

            Logger.debug("End: post_factor");
            return new _r_post_factor(postFactor.type, postFactor.text);
        }
        else if (lookahead.equals(Operator.mod())) {
            Logger.debug("Use: eq4");

            Operator op = match(Operator.mod(), Operator.class);

            _r_factor f = factor();

            if (!(l_type instanceof IntegerType) || !(f.type instanceof IntegerType)) {
                throw new UnexpectedType(op, new IntegerType(), new IntegerType(), l_type, f.type);
            }

            Type post_l_type = new IntegerType();
            String post_l_text = l_text + " MOD " + f.text;

            _r_post_factor postFactor = post_factor(post_l_type, post_l_text);

            Logger.debug("End: post_factor");
            return new _r_post_factor(postFactor.type, postFactor.text);
        }
        else if (
            lookahead.equals(Keyword.symEnd()) ||
            lookahead.equals(Keyword.symOf()) ||
            lookahead.equals(Keyword.symThen()) ||
            lookahead.equals(Keyword.symElif()) ||
            lookahead.equals(Keyword.symElse()) ||
            lookahead.equals(Keyword.symDo()) ||
            lookahead.equals(Operator.or()) ||
            lookahead.equals(Symbol.rpar()) ||
            lookahead.equals(Symbol.rbrack()) ||
            lookahead.equals(Operator.eq()) ||
            lookahead.equals(Operator.neq()) ||
            lookahead.equals(Operator.lt()) ||
            lookahead.equals(Operator.lteq()) ||
            lookahead.equals(Operator.gt()) ||
            lookahead.equals(Operator.gteq()) ||
            lookahead.equals(Operator.plus()) ||
            lookahead.equals(Operator.minus()) ||
            lookahead.equals(Symbol.semicol()) ||
            lookahead.equals(Symbol.comma())
        ) {
            // Epsilon production
            Logger.debug("Use: epsilon (eq5)");
            Logger.debug("End: post_factor");
            return new _r_post_factor(l_type, l_text);
        }
        else {
            throw new UnexpectedToken(new Token[] {
                Operator.mult(),
                Operator.and(),
                Operator.div(),
                Operator.mod(),
                Keyword.symEnd(),
                Keyword.symOf(),
                Keyword.symThen(),
                Keyword.symElif(),
                Keyword.symElse(),
                Keyword.symDo(),
                Operator.or(),
                Symbol.rpar(),
                Symbol.rbrack(),
                Operator.eq(),
                Operator.neq(),
                Operator.lt(),
                Operator.lteq(),
                Operator.gt(),
                Operator.gteq(),
                Operator.plus(),
                Operator.minus(),
                Symbol.semicol(),
                Symbol.comma()
            }, lookahead);
        }
    }

    private _r_factor factor() throws IOException, OberonException {
        Logger.debug("Start: factor");
        Logger.debug("Lookahead: " + lookahead.toString());

        if (lookahead.equals(new Identifier())) {
            Logger.debug("Use: eq1");

            Identifier id = match(new Identifier(), Identifier.class);

            Declaration decl = env.getDecl(id.getLexeme());
            if (decl == null) {
                throw new UnknownIdentifier(id);
            }

            _r_sel selection = sel(decl.getType());

            Logger.debug("End: factor");
            return new _r_factor(selection.type, id.getLexeme() + selection.text);
        }
        else if (lookahead.equals(new Number())) {
            Logger.debug("Use: eq2");

            Number num = match(new Number(), Number.class);

            Logger.debug("End: factor");
            return new _r_factor(new IntegerType(), ((Integer)num.getValue()).toString());
        }
        else if (lookahead.equals(Symbol.lpar())) {
            Logger.debug("Use: eq3");

            match(Symbol.lpar(), Symbol.class);

            _r_expr expr = expr();

            match(Symbol.rpar(), Symbol.class);

            Logger.debug("End: factor");
            return new _r_factor(expr.type, "(" + expr.text + ")");
        }
        else if (lookahead.equals(Operator.not())) {
            Logger.debug("Use: eq4");

            Operator op = match(Operator.not(), Operator.class);

            _r_factor f = factor();

            if (!(f.type instanceof BooleanType)) {
                throw new UnexpectedType(op, new BooleanType(), f.type);
            }

            Logger.debug("End: factor");
            return new _r_factor(new BooleanType(), "~(" + f.text + ")");
        }
        else {
            throw new UnexpectedToken(new Token[] {
                new Identifier(),
                new Number(),
                Symbol.lpar(),
                Operator.not()
            }, lookahead);
        }
    }

    private <T extends Token> T match(T token, Class<T> clazz) throws IOException, OberonException {
        if (lookahead.equals(token) && clazz.isInstance(lookahead)) {
            T tmp = clazz.cast(lookahead);
            lookahead = tokenHandle.nextToken();
            Logger.debug("Matched: " + tmp.toString());
            return tmp;
        } else {
            throw new UnexpectedToken(token, lookahead);
        }
    }
}
