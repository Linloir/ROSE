# Oberon-0 Language Analysis

## BNF Grammar

$$
\begin{aligned}
\textit{module} \rightarrow & \; \textbf{module} \; \textbf{id} \; \textbf{;} \; \textit{decls} \; \textit{stat\_block} \; \textbf{end} \; \textbf{id} \; \textbf{.} \\
\textit{stat\_block} \rightarrow & \; \textbf{begin} \; \textit{stat\_seq} \\
    & | \; \epsilon \\
\textit{decls} \rightarrow & \; \textit{cnst\_decl} \;\; \textit{type\_decl} \;\; \textit{var\_decl} \;\; \textit{proc\_decl\_seq} \\
\textit{cnst\_decl} \rightarrow & \; \textbf{const} \; \textit{cnst\_ass} \\
    & | \; \epsilon \\
\textit{cnst\_ass} \rightarrow & \; \textbf{id} \; \textbf{=} \; \textit{expr} \; \textbf{;} \; \textit{cnst\_ass} \\
    & | \; \epsilon \\
\textit{type\_decl} \rightarrow & \; \textbf{type} \; \textit{type\_ass} \\
    & | \; \epsilon \\
\textit{type\_ass} \rightarrow & \; \textbf{id} \; \textbf{=} \; \textit{type} \; \textbf{;} \; \textit{type\_ass} \\
    & | \; \epsilon \\
\textit{type} \rightarrow & \; \textbf{id} \\
    & | \; \textit{type\_arr} \\
    & | \; \textit{type\_rec} \\
    & | \; \textbf{integer} \\
    & | \; \textbf{boolean} \\
\textit{type\_arr} \rightarrow & \; \textbf{array} \; \textit{expr} \; \textbf{of} \; \textit{type} \\
\textit{type\_rec} \rightarrow & \; \textbf{record} \; \textit{field\_list} \; \textbf{end} \\
\textit{field\_list} \rightarrow & \; \textbf{id} \; \textbf{:} \; \textit{type} \; \textbf{;} \; \textit{field\_list} \\
    & | \; \epsilon \\
\textit{var\_decl} \rightarrow & \; \textbf{var} \; \textit{var\_list} \\
    & | \; \epsilon \\
\textit{var\_list} \rightarrow & \; \textit{id\_list} \; \textbf{:} \; \textit{type} \; \textbf{;} \; \textit{var\_list} \\
    & | \; \epsilon \\
\textit{proc\_decl\_seq} \rightarrow & \; \textit{proc\_decl} \; \textbf{;} \; \textit{proc\_decl\_seq} \\
    & | \; \epsilon \\
\textit{proc\_decl} \rightarrow & \; \textit{proc\_head} \; \textbf{;} \; \textit{proc\_body} \\
\textit{proc\_head} \rightarrow & \; \textbf{procedure} \; \textbf{id} \; \textit{proc\_pars} \\
\textit{proc\_pars} \rightarrow & \; \textbf{(} \; \textit{par\_list} \; \textbf{)} \\
    & | \; \epsilon \\
\textit{par\_list} \rightarrow & \; \textit{par} \; \textit{ext\_par} \\
\textit{par} \rightarrow & \; \textbf{var} \; \textit{id\_list} \; \textbf{:} \; \textit{type} \\
    & | \; \textit{id\_list} \; \textbf{:} \; \textit{type} \\
\textit{ext\_par} \rightarrow & \; \textbf{;} \; \textit{par} \; \textit{ext\_par} \\
    & | \; \epsilon \\
\textit{id\_list} \rightarrow & \; \textbf{id} \; \textit{ext\_id} \\
\textit{ext\_id} \rightarrow & \; \textbf{,} \; \textbf{id} \; \textit{ext\_id} \\
    & | \; \epsilon \\
\textit{proc\_body} \rightarrow & \; \textit{decls} \;\; \textit{stat\_block} \;\; \textbf{end} \; \textbf{id} \\
\textit{stat\_seq} \rightarrow & \; \textit{stat} \; \textit{ext\_stat} \\
\textit{ext\_stat} \rightarrow & \; \textbf{;} \; \textit{stat} \; \textit{ext\_stat} \\
    & | \; \epsilon \\
\textit{stat} \rightarrow & \; \textbf{id} \; \textit{id\_stat} \\
    & | \; \textit{if\_stat} \\
    & | \; \textit{while\_stat} \\
\textit{id\_stat} \rightarrow & \; \textit{assignment} \\
    & | \; \textit{proc\_call} \\
\textit{assignment} \rightarrow & \; \textit{sel} \; \textbf{:=} \; \textit{expr} \\
\textit{sel} \rightarrow & \; \textbf{.} \; \textbf{id} \; \textit{sel} \\
    & | \; \textbf{[} \; \textit{expr} \; \textbf{]} \; \textit{sel} \\
    & | \; \epsilon \\
\textit{proc\_call} \rightarrow & \; \textit{act\_pars} \\
\textit{act\_pars} \rightarrow & \; \textbf{(} \; \textit{expr\_list} \; \textbf{)} \\
    & | \; \epsilon \\
\textit{expr\_list} \rightarrow & \; \textit{expr} \; \textit{ext\_expr} \\
    & | \; \epsilon \\
\textit{ext\_expr} \rightarrow & \; \textbf{,} \; \textit{expr} \; \textit{ext\_expr} \\
    & | \; \epsilon \\
\textit{if\_stat} \rightarrow & \; \textbf{if} \; \textit{expr} \; \textbf{then} \; \textit{stat\_seq} \; \textit{elif\_seq} \; \textit{else\_stat} \; \textbf{end} \\
\textit{elif\_seq} \rightarrow & \; \textbf{elsif} \; \textit{expr} \; \textbf{then} \; \textit{stat\_seq} \; \textit{elif\_seq} \\
    & | \; \epsilon \\
\textit{else\_stat} \rightarrow & \; \textbf{else} \; \textit{stat\_seq} \\
    & | \; \epsilon \\
\textit{while\_stat} \rightarrow & \; \textbf{while} \; \textit{expr} \; \textbf{do} \; \textit{stat\_seq} \; \textbf{end} \\
\textit{expr} \rightarrow & \; \textit{simple\_expr} \; \textit{post\_expr} \\
\textit{post\_expr} \rightarrow & \; \textit{rel\_op} \; \textit{simple\_expr} \\
    & | \; \epsilon \\
\textit{rel\_op} \rightarrow & \; \textbf{=} \; | \; \textbf{\#} \; | \; \textbf{<} \\
    & | \; \textbf{<=} \; | \; \textbf{>} \; | \; \textbf{>=} \\
\textit{simple\_expr} \rightarrow & \; \textbf{+} \; \textit{term} \; \textit{post\_term} \\
    & | \; \textbf{-} \; \textit{term} \; \textit{post\_term} \\
    & | \; \textit{term} \; \textit{post\_term} \\
\textit{post\_term} \rightarrow & \; \textbf{+} \; \textit{term} \; \textit{post\_term} \\
    & | \; \textbf{-} \; \textit{term} \; \textit{post\_term} \\
    & | \; \textbf{or} \; \textit{term} \; \textit{post\_term} \\
    & | \; \epsilon \\
\textit{term} \rightarrow & \; \textit{factor} \; \textit{post\_factor} \\
\textit{post\_factor} \rightarrow & \; \textbf{*} \; \textit{factor} \; \textit{post\_factor} \\
    & | \; \textbf{\&} \; \textit{factor} \; \textit{post\_factor} \\
    & | \; \textbf{div} \; \textit{factor} \; \textit{post\_factor} \\
    & | \; \textbf{mod} \; \textit{factor} \; \textit{post\_factor} \\
    & | \; \epsilon \\
\textit{factor} \rightarrow & \; \textbf{id} \; \textit{sel} \\
    & | \; \textbf{num\_token} \\
    & | \; \textbf{(} \; \textit{expr} \; \textbf{)} \\
    & | \; \thicksim \; \textit{factor} \\
\end{aligned}
$$

## Syntax Directed Translation

$$
\begin{aligned}
\textit{s} \rightarrow & \; \{ \; env = null \; \} \\
    & \; \textit{module} \\
    & \; \{ \; for \; ( \; call \; \textbf{in} \; calls \; ) \quad call.verify() \; \} \\
    & \; \{ \; module.g\_mod.show() \; \} \\
\textit{module} \rightarrow & \; \textbf{module} \; \textbf{id} \; \textbf{;} \\
    & \; \{ \;
            decl = \textbf{new} \; Decl(\textbf{id}, \; \textbf{new} \; ModuleType()); \;
            env.put(decl) \;
        \} \\
    & \; \{ \;
            g\_mod = \textbf{new} \; Module(\textbf{id}.lexeme); \;
            module.g\_mod = g\_mod \;
        \} \\
    & \; \{ \;
            cur = env; \;
            env = \textbf{new} \; Env(env); \;
        \} \\
    & \; \{ \;
            decls.g\_mod = g\_mod; \;
        \} \\
    & \; \textit{decls} \\
    & \; \{ \;
            g\_proc = g\_mod.add(\text{\;}'Main' \;); \;
            stat\_block.g\_stats = g\_proc::add \;
        \} \\
    & \; \textit{stat\_block} \; \textbf{end} \; \textbf{id} \; \textbf{.} \\
    & \; \{ \;
            env = cur \;
        \} \\
\textit{stat\_block} \rightarrow & \; \textbf{begin} \\
    & \; \{ \; stat\_seq.g\_stats = stat\_block.g\_stats \; \} \\
    & \; \textit{stat\_seq} \\
    & | \; \epsilon \\
\textit{decls} \rightarrow & \; \textit{cnst\_decl} \;\; \textit{type\_decl} \;\; \textit{var\_decl} \\
    & \; \{ \; proc\_decl\_seq.g\_mod = decls.g\_mod \; \} \\
    & \; \textit{proc\_decl\_seq} \\
\textit{cnst\_decl} \rightarrow & \; \textbf{const} \; \textit{cnst\_ass} \\
    & | \; \epsilon \\
\textit{cnst\_ass} \rightarrow & \; \textbf{id} \; \textbf{=} \; \textit{expr} \\
    & \; \{ \; decl = \textbf{new} \; Decl(\textbf{id}, \; expr.type, \; \{ \; modifiers: [ \; Decl.Modifiers.Const \; ] \; \}) \; \} \\
    & \; \{ \; env.put(\textbf{id}.lexeme, \; decl) \; \} \\
    & \; \textbf{;} \; \textit{cnst\_ass}_1 \\
    & | \; \epsilon \\
\textit{type\_decl} \rightarrow & \; \textbf{type} \; \textit{type\_ass} \\
    & | \; \epsilon \\
\textit{type\_ass} \rightarrow & \; \textbf{id} \; \textbf{=} \; \textit{type} \\
    & \; \{ \; decl = \textbf{new} \; Decl(\textbf{id}, \; \textbf{new} \; Typedef(type.val)) \} \\
    & \; \{ \; env.put(\textbf{id}.lexeme, \; decl) \; \} \\
    & \; \textbf{;} \; \textit{type\_ass}_1 \\
    & | \; \epsilon \\
\textit{type} \rightarrow & \; \textbf{id} \\
    & \; \{ \;
            decl = env.get(\textbf{id}.lexeme); \;
            type.val = decl.type.val \;
        \} \\
    & | \; \textit{type\_arr} \\
    & \; \{ \; type.val = type\_arr.val \; \} \\
    & | \; \textit{type\_rec} \\
    & \; \{ \; type.val = type\_rec.val \; \} \\
    & | \; \textbf{integer} \\
    & \; \{ \; type.val = \textbf{new} \; IntegerType() \; \} \\
    & | \; \textbf{boolean} \\
    & \; \{ \; type.val = \textbf{new} \; BooleanType() \; \} \\
\textit{type\_arr} \rightarrow & \; \textbf{array} \; \textit{expr} \; \textbf{of} \; \textit{type} \\
    & \; \{ \;
            type\_arr.val = \textbf{new} \; ArrayType(type.val)
        \} \\
\textit{type\_rec} \rightarrow & \; \textbf{record} \\
    & \; \{ \; field\_list.l\_fields = \{ \; \} \; \} \\
    & \; \textit{field\_list} \; \textbf{end} \\
    & \; \{ \; type\_rec.val = \textbf{new} \; RecType(field\_list.fields) \; \} \\
\textit{field\_list} \rightarrow & \; \textbf{id} \; \textbf{:} \; \textit{type} \; \textbf{;} \\
    & \; \{ \; decl = \textbf{new} \; Decl(\textbf{id}, \; type.val, \; \{ \; modifiers: \; [ \; Decl.Modifiers.Var \; ] \; \}) \; \} \\
    & \; \{ \;
            field\_list.l\_fields.put(\textbf{id}.lexeme, \; decl); \;
            field\_list_1.l\_fields = field\_list.l\_fields \;
        \} \\
    & \; \textit{field\_list}_1 \\
    & \; \{ \; field\_list.fields = field\_list_1.fields \; \} \\
    & | \; \epsilon \quad \{ \; field\_list.fields = field\_list.l\_fields \; \} \\
\textit{var\_decl} \rightarrow & \; \textbf{var} \; \textit{var\_list} \\
    & | \; \epsilon \\
\textit{var\_list} \rightarrow & \; \textit{id\_list} \; \textbf{:} \; \textit{type} \; \textbf{;} \\
    & \; \{ \\
    & \quad for(id \; \textbf{in} \; id\_list.ids) \; \{ \\
    & \quad \quad env.put(\textbf{id}.lexeme, \; \textbf{new} \; Decl(\textbf{id}, \; type.val, \; \{ \; modifiers: \; [ \; Decl.Modifieres.Var \; ] \; \})); \\
    & \quad \} \\
    & \; \} \\
    & \; \textit{var\_list}_1 \\
    & | \; \epsilon \\
\textit{proc\_decl\_seq} \rightarrow & \; \{ \; proc\_decl.g\_mod = proc\_decl\_seq.g\_mod \; \} \\
    & \; \textit{proc\_decl} \; \textbf{;} \\
    & \; \{ \; proc\_decl\_seq_1.g\_mod = proc\_decl\_seq.g\_mod \; \} \\
    & \; \textit{proc\_decl\_seq}_1 \\
    & | \; \epsilon \\
\textit{proc\_decl} \rightarrow
    & \; \{ \;
            cur = env; \;
            env = \textbf{new} \; Env(env) \;
        \} \\
    & \; \textit{proc\_head} \; \textbf{;} \\
    & \; \{ \;
            proc\_body.g\_mod = proc\_decl.g\_mod; \;
            proc\_body.g\_proc = proc\_decl.g\_mod.add( proc\_head.id.lexeme ) \;
        \} \\
    & \; \{ \; proc\_body.decl\_id = proc\_head.id \; \} \\
    & \; \textit{proc\_body} \\
    & \; \{ \;
            env = cur; \;
            env.put(proc\_head.id, \; proc\_head.decl) \;
        \} \\
\textit{proc\_head} \rightarrow & \; \textbf{procedure} \; \textbf{id} \; \textit{proc\_pars} \\
    & \; \{ \; decl = \textbf{new} \; Decl(\textbf{id}, \; \textbf{new} \; FunctionType(proc\_pars.params)) \; \} \\
    & \; \{ \;
            proc\_head.decl = decl; \;
            proc\_head.id = \textbf{id} \;
        \} \\
\textit{proc\_pars} \rightarrow & \; \textbf{(} \; \textit{par\_list} \; \textbf{)} \quad \{ \; proc\_pars.params = par\_list.params \; \} \\
    & | \; \epsilon \quad \{ \; proc\_pars.params = [ \; ] \; \} \\
\textit{par\_list} \rightarrow & \; \textit{par} \; \textit{ext\_par} \\
    &  \; \{ \;
            par\_list.params = [ \; ...par.params, \; ...ext\_par.params \; ]
        \} \\
\textit{par} \rightarrow & \; \{ \; par.params = [ \; ] \; \} \\
    & \; \textbf{var} \; \textit{id\_list} \; \textbf{:} \; \textit{type} \\
    & \; \{ \\
    & \quad for(id \; \textbf{in} \; id\_list.ids) \; \{ \\
    & \quad \quad decl = \textbf{new} \; Decl(\textbf{id}, \; type.val, \; \{ \; modifiers: \; [ \; Decl.Modifiers.Var \; ] \; \}); \\
    & \quad \quad par.params.push(decl); \\
    & \quad \quad env.put(\textbf{id}.lexeme, \; decl); \\
    & \quad \} \\
    & \; \} \\
    & | \{ \;
            par.params = [ \; ] \;
        \} \\
    & \; \textit{id\_list} \; \textbf{:} \; \textit{type} \\
    & \; \{ \\
    & \quad for(id \; \textbf{in} \; id\_list.ids) \; \{ \\
    & \quad \quad decl = \textbf{new} \; Decl(\textbf{id}, \; type.val); \\
    & \quad \quad par.params.push(decl); \\
    & \quad \quad env.put(\textbf{id}.lexeme, \; decl); \\
    & \quad \} \\
    & \; \} \\
\textit{ext\_par} \rightarrow & \; \textbf{;} \; \textit{par} \; \textit{ext\_par}_1 \\
    & \; \{ \;
            ext\_par.params = [ \; ...par.params, \; ...ext\_par_1.params \; ] \;
        \} \\
    & | \; \epsilon \quad \{ \; ext\_par.params = [ \; ] \; \} \\
\textit{id\_list} \rightarrow & \; \textbf{id} \; \textit{ext\_id} \quad \{ \; id\_list.ids = [ \; \textbf{id}, \; ...ext\_id.ids \; ] \; \} \\
\textit{ext\_id} \rightarrow & \; \textbf{,} \; \textbf{id} \; \textit{ext\_id}_1 \quad \{ \; ext\_id.ids = [ \; \textbf{id}, \; ...ext\_id_1.ids \; ] \; \} \\
    & | \; \epsilon \quad \{ \; ext\_id.ids = [ \; ] \; \} \\
\textit{proc\_body} \rightarrow & \; \{ \; decls.g\_mod = proc\_body.g\_mod \; \} \\
    & \; \textit{decls} \\
    & \; \{ \; stat\_block.g\_stats = proc\_body.g\_proc::add \; \} \\
    & \; \textit{stat\_block} \;\; \textbf{end} \; \textbf{id} \\
\textit{stat\_seq} \rightarrow & \; \{ \; stat.g\_stats = stat\_seq.g\_stats \; \} \\
    & \; \textit{stat} \\
    & \; \{ \; ext\_stat.g\_stats = stat\_seq.g\_stats \; \} \\
    & \; \textit{ext\_stat} \\
\textit{ext\_stat} \rightarrow & \; \textbf{;} \quad \{ \; stat.g\_stats = ext\_stat.g\_stats \; \} \\
    & \; \textit{stat} \\
    & \; \{ \; ext\_stat_1.g\_stats = ext\_stat.g\_stats \; \} \\
    & \; \textit{ext\_stat}_1 \\
    & | \; \epsilon \\
\textit{stat} \rightarrow & \; \textbf{id} \\
    & \; \{ \;
            id\_stat.g\_stats = stat.g\_stats; \;
            id\_stat.id = \textbf{id}
        \} \\
    & \; \textit{id\_stat} \\
    & | \; \textit{if\_stat} \quad \{ \; stat.g\_stats.add(if\_stat.statement) \; \} \\
    & | \; \textit{while\_stat} \quad \{ \; stat.g\_stats.add(while\_stat.statement) \; \} \\
\textit{id\_stat} \rightarrow & \; \{ \; assignment.decl = env.get(id\_stat.id.getLexeme()); \; assignment.id = id\_stat.id \; \} \\
    & \; \textit{assignment} \\
    & \; \{ \; id\_stat.g\_stats.add(assignment.statement) \; \} \\
    & | \; \{ \; proc\_call.decl = env.get(id\_stat.id.getLexeme()); \; proc\_call.id = id\_stat.id \; \} \\
    & \; \textit{proc\_call} \\
    & \; \{ \; id\_stat.g\_stats.add(proc\_call.statement) \; \} \\
\textit{assignment} \rightarrow & \; \{ \; decl = assignment.decl; \; sel.parent\_type = decl.type \; \} \\
    & \; \textit{sel} \; \textbf{:=} \; \textit{expr} \\
    & \; \{ \; assignment.statement = \textbf{new} \; PrimitiveStatement(decl.lexeme \; || \; sel.text \; || \; \text{}':=' \; || \; expr.text) \; \} \\
\textit{sel} \rightarrow & \; \textbf{.} \; \textbf{id} \\
    & \; \{ \; type = sel.parent\_type.fields.get(\textbf{id}); \; sel_1.parent\_type = type \; \} \\
    & \; \textit{sel}_1 \\
    & \; \{ \;
            sel.text = \text{}'.' \; || \; \textbf{id}.lexeme \; || \; sel_1.text; \;
            sel.type = sel_1.type \;
        \} \\
    & | \; \textbf{[} \; \textit{expr} \; \textbf{]} \\
    & \; \{ \; sel_1.parent\_type = sel.parent\_type.type \; \} \\
    & \; \textit{sel}_1 \\
    & \; \{ \;
            sel.text = \text{}'[' \; || \; expr.text \; || \; \text{}']' \; || \; sel_1.text; \;
            sel.type = sel_1.type \;
        \} \\
    & | \; \epsilon \quad \{ \; sel.text = \text{}''; \; sel.type = sel.parent\_type \; \} \\
\textit{proc\_call} \rightarrow & \; \{ \; decl = proc\_call.decl \; \} \\
    & \; \textit{act\_pars} \\
    & \; \{ \; calls.add(\textbf{new} ProcCall(proc\_call.id, \; act\_pars.types)) \; \} \\
    & \; \{ \; proc\_call.statement = \textbf{new} \; PrimitiveStatement(decl.lexeme \; || \; act\_pars.text) \; \} \\
\textit{act\_pars} \rightarrow & \; \textbf{(} \; \textit{expr\_list} \; \textbf{)} \\
    & \; \{ \; act\_pars.types = expr\_list.types \; \} \\
    & \; \{ \; act\_pars.text = \text{}'(' \; || \; expr\_list.text \; || \; \text{}')' \; \} \\
    & | \; \epsilon \\
    & \; \{ \; act\_pars.types = [ \; ] \; \} \\
    & \; \{ \; act\_pars.text = \text{}'' \; \} \\
\textit{expr\_list} \rightarrow & \; \textit{expr} \; \textit{ext\_expr} \\
    & \; \{ \; expr\_list.types = [ \; expr.type, \; ...ext\_expr.types \; ] \; \} \\
    & \; \{ \; expr\_list.text = expr.text \; || \; ext\_expr.text \; \} \\
    & | \; \epsilon \\
    & \; \{ \; expr\_list.types = [ \; ] \; \} \\
    & \; \{ \; expr\_list.text = \text{}'' \; \} \\
\textit{ext\_expr} \rightarrow & \; \textbf{,} \; \textit{expr} \; \textit{ext\_expr} \\
    & \; \{ \; ext\_expr_1.types = [ \; expr.type, \; ...ext\_expr.types \; ] \; \} \\
    & \; \{ \; ext\_expr.text = \text{}',' \; || \; expr.text \; || \; ext\_expr_1.text \; \} \\
    & | \; \epsilon \\
    & \; \{ \; ext\_expr.types = [ \; ] \; \} \\
    & \; \{ \; ext\_expr.text = \text{}'' \; \} \\
\textit{if\_stat} \rightarrow & \; \textbf{if} \; \textit{expr} \\
    & \; \{ \; statement = \textbf{new} \; IfStatement(expr.text) \; \} \\
    & \; \textbf{then} \\
    & \; \{ \;
            g\_tbody = statement.getTrueBody(); \;
            stat\_seq.g\_stats = g\_tbody::add \;
        \} \\
    & \; \textit{stat\_seq} \\
    & \; \{ \;
            g\_fbody = statement.getFalseBody(); \;
            elif\_seq.g\_stats = g\_fbody::add \;
        \} \\
    & \; \textit{elif\_seq} \\
    & \; \{ \;
            g\_stats = elif\_seq.g\_pstats; \;
            else\_stat.g\_stats = g\_stats \;
        \} \\
    & \; \textit{else\_stat} \; \textbf{end} \\
    & \{ \; if\_stat.statement = statement \; \} \\
\textit{elif\_seq} \rightarrow & \; \textbf{elsif} \; \textit{expr} \\
    & \; \{ \; statement = \textbf{new} \; IfStatement(expr.text) \; \} \\
    & \; \textbf{then} \\
    & \; \{ \;
            g\_tbody = statement.getTrueBody(); \;
            stat\_seq.g\_stats = g\_tbody::add \;
        \} \\
    & \; \textit{stat\_seq} \\
    & \; \{ \;
            g\_fbody = statement.getFalseBody(); \;
            elif\_seq_1.g\_stats = g\_fbody::add \;
        \} \\
    & \; \textit{elif\_seq}_1 \\
    & \; \{ \;
            elif\_seq.g\_stats.add(statement); \;
            elif\_seq.g\_pstats = elif\_seq_1.g\_pstats \;
        \} \\
    & | \; \epsilon \quad \{ \; elif\_seq.g\_pstats = elif\_seq.g\_stats \; \} \\
\textit{else\_stat} \rightarrow & \; \textbf{else} \\
    & \; \{ \; stat\_seq.g\_stats = else\_stat.g\_stats \; \} \\
    & \; \textit{stat\_seq} \\
    & | \; \epsilon \\
\textit{while\_stat} \rightarrow & \; \textbf{while} \; \textit{expr} \\
    & \; \{ \; statement = \textbf{new} \; WhileStatement(expr.text) \; \} \\
    & \; \textbf{do} \\
    & \; \{ \;
            g\_lbody = statement.getLoopBody(); \;
            stat\_seq.g\_stats = g\_lbody::add \;
        \} \\
    & \; \textit{stat\_seq} \; \textbf{end} \\
    & \; \{ \; while\_stat.statement = statement \; \} \\
\textit{expr} \rightarrow & \; \textit{simple\_expr} \\
    & \; \{ \; post\_expr.l\_type = simple\_expr.type \; \} \\
    & \; \{ \; post\_expr.l\_text = simple\_expr.text \; \} \\
    & \; \textit{post\_expr} \\
    & \; \{ \; expr.type = post\_expr.type \; \} \\
    & \; \{ \; expr.text = post\_expr.text \; \} \\
\textit{post\_expr} \rightarrow & \; \textit{rel\_op} \; \textit{simple\_expr} \\
    & \; \{ \; post\_expr.text = post\_expr.l\_text \; || \; rel\_op.text \; || \; simple\_expr.text \; \} \\
    & \; \{ \; post\_expr.type = \textbf{new} \; BooleanType() \; \} \\
    & | \; \epsilon \\
    & \; \{ \; post\_expr.text = post\_expr.l\_text \; \} \\
    & \; \{ \; post\_expr.type = post\_expr.l\_type \; \} \\
\textit{rel\_op} \rightarrow & \; \textbf{=} \quad \{ \; rel\_op.text = \text{}'=' \; \} \\
    & | \; \textbf{\#} \quad \{ \; rel\_op.text = \text{}'\#' \; \} \\
    & | \; \textbf{<} \quad \{ \; rel\_op.text = \text{}'<' \; \} \\
    & | \; \textbf{<=} \quad \{ \; rel\_op.text = \text{}'<=' \; \} \\
    & | \; \textbf{>} \quad \{ \; rel\_op.text = \text{}'>' \; \} \\
    & | \; \textbf{>=} \quad \{ \; rel\_op.text = \text{}'>=' \; \} \\
\textit{simple\_expr} \rightarrow & \; \textbf{+} \; \textit{term} \\
    & \; \{ \; post\_term.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_term.l\_text = \text{}'(+' \; || \; term.text \; || \; \text{}')' \; \} \\
    & \; \textit{post\_term} \\
    & \; \{ \; simple\_expr.type = post\_term.type; \; simple\_expr.text = post\_term.text \; \} \\
    & | \; \textbf{-} \; \textit{term} \\
    & \; \{ \; post\_term.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_term.l\_text = \text{}'(-' \; || \; term.text \; || \; \text{}')' \; \} \\
    & \; \textit{post\_term} \\
    & \; \{ \; simple\_expr.type = post\_term.type; \; simple\_expr.text = post\_term.text \; \} \\
    & | \; \textit{term} \\
    & \; \{ \; post\_term.l\_type = term.type; \; post\_term.l\_text = term.text \; \} \\
    & \; \textit{post\_term} \\
    & \; \{ \; simple\_expr.type = post\_term.type; \; simple\_expr.text = post\_term.text \; \} \\
\textit{post\_term} \rightarrow & \; \textbf{+} \; \textit{term} \\
    & \; \{ \; post\_term_1.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_term_1.l\_text = post\_term.l\_text \; || \; \text{}'+' \; || \; term.text \; \} \\
    & \; \textit{post\_term}_1 \\
    & \; \{ \; post\_term.type = post\_term_1.type; \; post\_term.text = post\_term_1.text \; \} \\
    & | \; \textbf{-} \; \textit{term} \\
    & \; \{ \; post\_term_1.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_term_1.l\_text = post\_term.l\_text \; || \; \text{}'-' \; || \; term.text \; \} \\
    & \; \textit{post\_term}_1 \\
    & \; \{ \; post\_term.type = post\_term_1.type; \; post\_term.text = post\_term_1.text \; \} \\
    & | \; \textbf{or} \; \textit{term} \\
    & \; \{ \; post\_term_1.l\_type = \textbf{new} \; BooleanType() \; \} \\
    & \; \{ \; post\_term_1.l\_text = post\_term.l\_text \; || \; \text{}'or' \; || \; term.text \; \} \\
    & \; \textit{post\_term}_1 \\
    & \; \{ \; post\_term.type = post\_term_1.type; \; post\_term.text = post\_term_1.text \; \} \\
    & | \; \epsilon \\
    & \; \{ \; post\_term.type = post\_term.l\_type \; \} \\
    & \; \{ \; post\_term.text = post\_term.l\_text \; \} \\
\textit{term} \rightarrow & \; \textit{factor} \\
    & \; \{ \; post\_factor.l\_type = factor.type; \; post\_factor.l\_text = factor.text \; \} \\
    & \; \textit{post\_factor} \\
    & \; \{ \; term.type = post\_factor.type; \; term.text = post\_factor.text \; \} \\
\textit{post\_factor} \rightarrow & \; \textbf{*} \; \textit{factor} \\
    & \; \{ \; post\_factor_1.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_factor_1.l\_text = post\_factor.l\_text \; || \; \text{}'*' \; || \; factor.text \; \} \\
    & \; \textit{post\_factor}_1 \\
    & \; \{ \; post\_factor.type = post\_factor_1.type; \; post\_factor.text = post\_factor_1.text \; \} \\
    & | \; \textbf{\&} \; \textit{factor} \\
    & \; \{ \; post\_factor_1.l\_type = \textbf{new} \; BooleanType() \; \} \\
    & \; \{ \; post\_factor_1.l\_text = post\_factor.l\_text \; || \; \text{}'\&' \; || \; factor.text \; \} \\
    & \; \textit{post\_factor}_1 \\
    & \; \{ \; post\_factor.type = post\_factor_1.type; \; post\_factor.text = post\_factor_1.text \; \} \\
    & | \; \textbf{div} \; \textit{factor} \\
    & \; \{ \; post\_factor_1.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_factor_1.l\_text = post\_factor.l\_text \; || \; \text{}'div' \; || \; factor.text \; \} \\
    & \; \textit{post\_factor}_1 \\
    & \; \{ \; post\_factor.type = post\_factor_1.type; \; post\_factor.text = post\_factor_1.text \; \} \\
    & | \; \textbf{mod} \; \textit{factor} \\
    & \; \{ \; post\_factor_1.l\_type = \textbf{new} \; IntegerType() \; \} \\
    & \; \{ \; post\_factor_1.l\_text = post\_factor.l\_text \; || \; \text{}'mod' \; || \; factor.text \; \} \\
    & \; \textit{post\_factor}_1 \\
    & \; \{ \; post\_factor.type = post\_factor_1.type; \; post\_factor.text = post\_factor_1.text \; \} \\
    & | \; \epsilon \\
    & \; \{ \;post\_factor.type = post\_factor.l\_type \; \} \\
    & \; \{ \; post\_factor.text = post\_factor.l\_text \; \} \\
\textit{factor} \rightarrow & \; \textbf{id} \\
    & \; \{ \; decl = env.get(\textbf{id}.lexeme); \; sel.parent\_type = decl.type \; \} \\
    & \; \textit{sel} \\
    & \; \{ \; factor.type = sel.type; \; factor.text = \textbf{id}.lexeme \; || \; sel.text \; \} \\
    & | \; \textbf{num\_token} \\
    & \; \{ \; factor.type = \textbf{new} \; IntegerType(); \; factor.text = \textbf{num\_token}.lexeme \; \} \\
    & | \; \textbf{(} \; \textit{expr} \; \textbf{)} \\
    & \; \{ \; factor.type = expr.type; \; factor.text = \text{}'(' \; || \; expr.text \; || \; \text{}')' \; \} \\
    & | \; \thicksim \; \textit{factor}_1 \\
    & \; \{ \; factor.type = \textbf{new} \; BooleanType(); \; factor.text = \text{}'\thicksim' \; || \; factor_1.text \; \} \\
\end{aligned}
$$
