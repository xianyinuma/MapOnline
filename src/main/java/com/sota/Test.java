package com.sota;

import com.sota.controller.UserController;
import com.sota.message.Base64String;
import com.sota.message.EditResponse;
import com.sota.message.UploadResponse;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by wenjin on 2017/5/8.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        String base64 = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a\n" +
                "HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy\n" +
                "MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAWgDASIA\n" +
                "AhEBAxEB/8QAHAAAAgMAAwEAAAAAAAAAAAAAAAMBAgQFBgcI/8QAPxAAAgEDAgQDBQUGBQMFAAAA\n" +
                "AAECAwQRBRIGITFhB0FREyJxgaEyYpGxwRQjM0JScggVNILRY5LhJCVEU8L/xAAaAQADAQEBAQAA\n" +
                "AAAAAAAAAAAAAQIDBAUG/8QAIhEBAQACAgICAwEBAAAAAAAAAAECEQMSITEEQRMyUSJh/9oADAMB\n" +
                "AAIRAxEAPwD2xMsmKTLpmZmJlkxaZZMYMRZC0y6KBiLIoiyAlgABgBkAABE+RAMArJnzZ49znV4v\n" +
                "2SeYU7WmorHTOWfSMj5z8cUpcYTWObtaXP8AE04/2Zc11Jf+vFQ8hzt6jk0oNkO3nH7eI/FiuNab\n" +
                "lKAmSin9rJAtB3Pwr4hhw14h6Zd1ZKNvWk7as2ukZ8s/J4fyPsnJ8CJtNNPDR2qHiXxrBKMeJtSS\n" +
                "Swl7YWtm+0QPkfTPGjjmwcd2q/tUF1jc0Yzz88J/U9D4b/xCRqzhR4i0v2eXh3Fm20u7g+f4Mdwp\n" +
                "do90yRkxaZqtjrWn0r/Trqnc2tVZhUpvKfbs+zNmSTTkkrkkAsGSAyASQRkAAIBsq2I0tlWyGyrk\n" +
                "AWbKtlWyrkGws2Q2UcirkTsLORRyKORVyEFnIo5ZKORRzFsLuQCXPmAg0Jl0xEZF0x7B6l3LpiVI\n" +
                "umMGpl0xSZdMcByZZC4l0UF0SQiRkAIDyADOCHIiTwdA448TbDhffZ2ijd6njDp59yl/e/X7q+eB\n" +
                "yW3UTllMZuu631/a6fazuby5pW9CH2qlWajFfNnzT4pcTaVrvFNS6sKsq1vGjCmp7XFSazlpPyOB\n" +
                "4k4t1biK6dxqN1OtJfYh0hT/ALYrkjplxVnUqtyeWbY4/j81jv8AN4+j7i/nNtQ9yP3fMxttvL5k\n" +
                "ARlla2xxmM8AAAgwWprM0ipaEtsk/QrG+T+nMW9pF013HOyjt6vsWsK0asE0zk4wW3KydckseZyc\n" +
                "uWOWq7V4ScSXOgcU0LGdSTsb+ao1abfLc+UZ49U+XwZ9Lnzj4Z8NVdY4qt7nY1bWk41qk8cuTyl8\n" +
                "Wz6NRzc2u3h1/GzuWO6sixVFjF0pDJBDfIZJyRnkQQ2Iw2Q2Q2UbAJcijkQ5C5SJtCzkVcijkUch\n" +
                "bC7kUcijmUcxbC7mUcxbmUlIm0l3Mo59xcplHMWwY5cwEOQCDbGQ1SMkZDoyyVKGhMYmIjIYmVKZ\n" +
                "6YyLM8WNixwHxYxCYsbEuBdMsVRYZAqyxDAOgeJvGdThrS4WljNR1C6i2p+dKHRy+L6L8T50r+1r\n" +
                "1ZVKsnKcm223nLPYvGjRbuV9b6vGDnaOiqM5Jfw5JtrPonk8lkouGMc+51cMnXbzPk8mX5NVxN1F\n" +
                "7GkkY6GhXV3YO8opSgpbZJfyvyy++H+BzFWipJ5WRmmqrTdW1pOXsq+IzhB83z5OPdZx8Gx5za+L\n" +
                "l1jqOqSt5wk4tc08MVKLjJppprqjlq1OVC/qSnDMYv316LoX1KypTpxr05Ldhbl6r1M7hv06py71\n" +
                "v7cKBeVKUc5XQoZWaagAAQabO6lb1k/5W+aPXeCvDnVeKqNG+n/6XTZ81WnzdRfdXmeMnuHgb4hR\n" +
                "say4X1Svttqss2lSb5Qm+sPg/wA/iazOyajHl4cc7LXuWhaBY8PadCysKWynHnKT5ym/Vs5QkDC3\n" +
                "beYyTUBPkQABJDYENgA2Vb5A2UbAw2LlLBMmKlIm0IlIW5ESkLlMm0LORRyKORRyJ2F3IpKRRyKO\n" +
                "YtktKfcW5lXIo5CCzkUcirkLcu4J2u5AJcgGTbGXoOjIxQmPhMSpWyMhsZGWMhsZDlU0xkNizNGQ\n" +
                "6DLlDTFjoszwY6LLgNRcWmXQwkMEoBkVVo069OdKrTjUpzWJQmsprujomteEXDuqSdS2VXT6j5/u\n" +
                "HmH/AGvp8j0EjA5lZ6Tlhjl+0eJXXgbeJv8AZNZoTj5KrSlF/TJ0nXuGLngHXbGV9d29apmNxihl\n" +
                "7IxkubTXm/yPqPB5nx74Y1+J9YnrFvqsqE40Yr2Hst2+UOaWc9OxpOW3xXPn8fGT/LwjVvYVdVvv\n" +
                "Ypba9CWU1hxk4ua/BxX4nVpOo7FTz7qe1Hbb3T6lnc6hUrqaqQdSVRz65w+T+iOnVa8VYwoRfPc5\n" +
                "SL9QuGzKan0z+0ljGSj6gBlvbrAAAgC1OpOlUjOEnGUXlNeRUAge9cEeO9SFrQ07XLCvdVoJQhXt\n" +
                "lunPyWY+bPcdMv1qVhSulb3Fuqiz7O4hsmvij4YoV6ttWhWoVJ06sHujODw4v1TPY+EvH/U7D2Vr\n" +
                "xHaq/t1iLuaPu1kvVrpL6MMp/Cksr6QyGTheHuKtF4qsv2rRr+lcxSW+CeJ0+0ovmjmMkKSVbwGS\n" +
                "kmADZRsGxcpE2gSYmUglITOfIm0IlMVKZEpipTItJdyKOYtyKOYhsxyKOQtzKOYFtdyKORRzKOQJ\n" +
                "2u5i3Iq5C3LuPRbXcwEuYD0W2uE+ZohMwwkPhIlUrdCQ2MjJCQ6MgXGuEh8JZMcWaIPoVKGyDHxf\n" +
                "IywfM0QZcM9MumKiy5YMTJKosMkgAAAdJ4u8U+GOD1OldXn7VfR6Wdq1OefvPpH5nQ/Hbj3UNKqU\n" +
                "NA0i8qW0pw33VSlLE2n0hnqljm8dj52cnJtttt8235j6iXbtfGPHV3xVdzVK2p2FgpzlTtqXN+88\n" +
                "tyl1k21n0OpgAykk9AAADAAAGAAAAAAGG7StX1DQ9Qp32mXlW1uqb92pSlh/B+q7M+nfC7xRo8bW\n" +
                "70/UIwoa1RhulGPKFeK6yj6P1R8qG7R9WvND1a11OwquldW1RVKcu/o+zXJ/EVhPuNspJnD8LcR2\n" +
                "3FXDNlrFriMa8Pfhn+HNcpRfwf6HKtmdNDkKlIJSFSkRaETl1ETmTOZnnMkhKYiU+5E5iJTJTaa5\n" +
                "C3MU59yrmBbMcyrkKcyrmPRbMcirn3FuYt1B6TaY5lHMVKZRzK0m0yUwM7qAPSezkIsfCRlg8joM\n" +
                "itY1wkPg8mSDHxZK41QZopsyQfM0U2OKbYPoaYMx02aYMuG0xZdCosZE0C66l0URdASxm1C+oabp\n" +
                "9xfXM1Chb05VKkn5JLJoPJvHviP/AC3ha30ajPFbUamaiT5qlDm/xe1fiOeaV8R8/wDGWv1eJOIr\n" +
                "vUqr51qspJf0x8l+COujKrbk8izTIYzUAABJgAAAAABAAAFGAAAAJXUglLmkAe4/4fdelC51PQKs\n" +
                "/cqQV3RTfSS92a+aafyPc5yPkvw01R6Tx/o1w5ONOVdUZ484z939UfV05Yyn1Rln7ISkJnPqROYm\n" +
                "czLYE5mapUCpPqZak8iTamdQTKZSdQTKoPSLTXMq59xDn3KuoVpPY5zKubEOoUdQek9j3PAuVTAl\n" +
                "1O4t1B6RcjpVBcqgl1O4uVTHmORNyOdQDK6gFdUdnOwkPgzLF88j4SMa6pWqMh0XkyxY6MsEVcrX\n" +
                "BmmmzFCRqpsIuN1NmmDMVNmqDLimqLGRYmLGJmkByLIWmXQwvk+UvGvW3qviNeUYybpWFONrD4rn\n" +
                "L6t/gfU15dQsrG4u6jShQpyqyb9IrP6Hw9ql/V1TVLu/rturc1p1ZPvJt/qXijJx8nzyVJZA6qAA\n" +
                "AQAAAAAAAAAABgAAZAvFfvUl6lBkV+9j3WQB9lXlb3NKvF4lTnGax6p5/Q+xre7jdWlC5j9mtTjU\n" +
                "XzSf6nxpR6o+qOC7x3XBGjVZPLdrCL+XL9DLk9B2Kc+4icyspmedQxTaKlQzVJk1J9TJUn1KkZ5V\n" +
                "M6ncTKp3FzqdxEqhcjG5HuqUdTn1EOoUdTuVpFyaHUKOoZ3UFuoPqm5tLqC3U7md1CjqdxyJuR8q\n" +
                "gt1BLn3KOoVIi5HOfcDM5gVpHZ2mMh0WZ4vmNizlrvlaYsdGRmi+Q2Mia0jXCRqpyMMGaachLlch\n" +
                "SkaoMw030NdOQ4trixqZmix0WXDPixiERYyLLDqHixqT0zwy1qpGWJ1aSoR/3yUX9Gz5ClyPpb/E\n" +
                "DeujwPZ2q/8AkX0c/CMW/wDg+aprFNP16GuHpnfZDIJZAqsAACAAAAAAAAAAAMAADAH01zpSXXmh\n" +
                "Bpt+cU+u2T+qCFS6T95H0f4Y3HteANPWedOVSHwxN/8AJ830+qPfPCOtv4OqU8/w7qax8UmZ8k8J\n" +
                "yd+lMTOYSlgz1JmMjO1WpPqZKlQtVn15mOpUNJGOWQnUEyqcyk6giUzSRhlka6hR1O4lzKOZUjO5\n" +
                "HOoUcxLn3KOZWk3I5z7lXUEuRRzHpNyOcyrmJcyu8ekXI7cAjcA9F2dziOQpDUcb1YbEbFiYDok1\n" +
                "cOj1NNN9DLEfTfMlcb6b6GqmzFTZqpsI0a4sbGRmgx0WXDaIsamIixiZUDxv/EFuuLbh+0j/ADVa\n" +
                "032SUVn8zwG8xui0sRa91dj3Xx+uNlTSqcf4jpTjHtuks/keG6svZ38qPlSiofguf1Oifqwl3nWA\n" +
                "AAluAABEAAAAAAEAAAMwAAMA12LWaqf9DZkNVi/38l6wkvoPH2nL0RT+0ez+Dl0v2DUrZvmqkKiX\n" +
                "yw/0PF4dUeoeEVz7PVbyh/8AZQyvk0yMpuJz9PYak+5kqT6l6lTuY6tQykYZZK1J8jFUqF6tQx1J\n" +
                "muMc2eQnMRKZWcxMpmkjC5GuZRzFOZVzLkZ3IxzKuYtyKbu49JuRjn3IchbkVyPSdmOXcjcLyyMh\n" +
                "otmbgF5Aei278uoxC11GI4HtQxDUJTGxZNXDoj4PmZosfBk1cbKTNUWY6bNMHyEuNUXyGxZmixsZ\n" +
                "FSm1RYyMjPGRdSjh7niPn8C4K+fPFDVqeveIFClCWbWyk6W5c1LZzk12zlfI8juqrr3NWq+s5OX4\n" +
                "s7brNe2oO8q2k6kqX7ynRdT7WJVJf/lHTpHTfE0w4d221UAAh0AAACAAAGAAAIAAAYAAGAOtXi5h\n" +
                "nz5fihJei9taEvSSYQr6QuTO9eGlx7HiijHynGcX/wBr/wCDo9Rba016SZ2rgSsqPEtrJ/1bfxTD\n" +
                "TPk/Xb3GrVMlSr3FTuMrqZp1SZi4MuRepUMtSpzInUz5iJzNJHPlkJzFSkVlMW5GkjG1dyKORRsh\n" +
                "yHpNq7kVyV3FWytJ2ZkjIvIZDRbX3EZK57kZGW19wFdwAe3oKYyLM6YxSPOe3KemMixEZDIsVVto\n" +
                "ix8GZYsdCRNXG2mzRB8jHTkPjIlpGqMhsZGVSLqYKbIzOI4u1VaRwhql7uxKnbyUHnHvS5L8zepn\n" +
                "m3jVqrocL2unQk1O8r5kl/TH/wAtGmHnJOV1Hhuqy9naWdLOXKmqsvn0/X8Th31NN7Wda4bzlRSj\n" +
                "H4JYMp1ZUuOagAAIaAAACAAAAAACAAAGAAAMwTF4kmQAoTRdrbeVMeuTleG6/sNXt55w1Uhz/wBx\n" +
                "xFxn2yb6uMX9DXpk9l1Gf9LjL8JIqe2fJN4V7W63Uo6vcyurzKuoadXiXM+VQVOYtzKORUiLks5F\n" +
                "HIpKXMo58ytM7kY5FdxRyI3D0W19xDkU3Ebhp2vkMlM/EMgNr5DJTcGQG18gUyADb0BSLqRkUxqm\n" +
                "cFj2ZWqMxkZGRTGxmybGkrXGXcfCRijMfCRNi5W6Eh8JcupihIdGRFjWVrUyd5m9pgj2oj22e0we\n" +
                "E+Lmrq+4p/Zov3NPpbP9zSf5v6HsV7qVDT7Krd3VVU6FGO6cn5I+bOKLn9r1q8uN8puvW3Zl1x1/\n" +
                "U34cftGWW7I69IqTIg1raAAARgAACAAAGAAAAAAGAAAAAAAg0XHP2MvWmi9pLEpP7ouq80KD9E19\n" +
                "Sbf7T/tZX2zv6vW4VM0oSznMU/oTvMNnV3WFu/WnH8hu86ZHzmd1lYe5lXPl1FbmV3D0jZjkRu9S\n" +
                "m4NyGS2QyV3BuALZDJTcg3APC+exGexXcTleoGnJJTcG4C8L5AqpAAd0UxkZmJTGxmcVj1Jk2xmN\n" +
                "jMxRnyGxmTY0mTbGY+nMwxmOhImxpjk5CExymYYTHKfIixtMmn2guVXuIlUwUjOMp5qSUaUVuqSf\n" +
                "8sV1YSFcnSPFDXbSOhVtIjXze1HTqOnHygpefp8DyCv78aMpdXFyk/mbOJb56hxLqV3u3KrXm4tf\n" +
                "055fTBl62tSb/lhGK+Z1YY6mj/lcZLqQS+pBNdEAAAgAAAAAAAAAADAAAAAADAAAER8lmzpy9Jtf\n" +
                "kTbP96/7X+RCWbGT/pqL6oLX+Ol6pr6FfZfT0LSqm7SLR/8ATSNe84nQam7RLfD6Zj9TkXLB14+Y\n" +
                "+a55rlyn/Td4OYncTl9CtMTNyDcKz8iUwHk3cG4WmGQBm5BuRTIADNwZF7gz3FobMygKZJyAW/EC\n" +
                "MgAdpjMZGZjUxkZnLY75k2xmMjMxRmNjMmxpMm6Ex8JmCM+g6FQixrjk5GExvtMGKFQY6nIjTaZG\n" +
                "zq9zqviBrP8AlXDLtqdTbc3zxhdVTXV/P/k7RZ04XNz++lst6adStP8Apguv/B4px/rf+d8V3deK\n" +
                "20Yy2U4LpGK5JF4Y+dlP9XTrEnlc+o6U9umSX9dT8kZ2TVli1hH7zZq6demV9SAYENgAAIwSQAiA\n" +
                "AAwAAAMAAAQAAAwAABNNHDsrhem1/UpbPFxH5/kTb52V0vOmytv/AKiHxH/C/rt3Dc86Ol6VJI5j\n" +
                "cdf4bl/7fUXpU/Q5tPJ14fq+f+XjrmyNyCkLyGS3MbuDORal2J3IAZknkL3AmA0ZyJWReScgF89g\n" +
                "T5ldwKQFpfOETuKZQcgHlfIFfMAGnPKZeMzIpjFIxsbzJsjMbGZiUxsZk2NJk2xmNhUMMZjI1MPq\n" +
                "RY0xycnCo/UircQp03Oc4wgublJ4SR1vV+J6Gk5pKDrV8fZTwo/FnRr/AIluNUun+3tu2UJqNGny\n" +
                "Sk4tRffDJ6unDHLJ3TVOO7OtwhdUbPMatW6lTlPPNwilt+Ty38jymrOVSpKcnlvm2V3ySccvD5tF\n" +
                "Sp4jqw45jbRn0CvL93BeiH2lpUvLmFClHMpvAm9j7K5nSznY9v4BfS5Z20ygAENwT5kAIgAAIAAA\n" +
                "ewAAA0AAABgAAAAAAI+151JR9YNfQpb/AOop/wByL2clG6hno8r6C6a/fQX3kV/Cdw4J1OjZQu6F\n" +
                "1bwr0Kk4uUJr480+qZ3hcOWmsW8q+gXanWSy7GvJKp/sl0l8Op5focttW4j3R2OjXnSkp05uMl5p\n" +
                "m+Etm5XlfK8cl3NxprU6lvWnRqwlCpB4lGSw0+4vdyNNxqU7+koXcm5p5VdLMvn6mGblT5zw4eVS\n" +
                "P2X8fQ1lv24em/R27uTuEqXJFk+403E3PYMoVu9CdwF1Nz6E7mKUidwFo3cSpIUpE7gKw3cTuE57\n" +
                "k5fxAtG5AXu7AAcqpjFMyqZdTJ0JWyMxkZmJTGKoTYuZNkZjI1ORjVQvGZOmkydU4we7Wakkklsh\n" +
                "0X3UdSq9ep3/AIgs/bJ3Di5QdPbJpc4tc0zo17bOjXlCL3xXNSS6r1Is09P4/JMsdMbLUqU69WNK\n" +
                "lBznJ4UUupXY3LCTyd/4W4dhYXVSWp1Ha1o08yhOD9ovPbFPzaa69Mik225OSYY7V0XR6ek0VOeJ\n" +
                "XMl78v6eyPP7uW+7rS9Zt/U9V1G7t42tedO19nJZlFqbeIpdO788nk1R5m35tj5PEjD4OWWeWWWS\n" +
                "gABi9IAACIAAAAAAAAAAAAADMAAAAAAANt3i4pv7yIfu3D7T/UrTeKkX3GXC23dRffY0/bfpc9t7\n" +
                "XXrn8znYVDrln7l/U+ZzMKnI6OO+HB8rDeTd7QlVXF8pNGXeSpGrk/G1RkvLkvRF1L5mVTLqQIuL\n" +
                "QpE7hCmWUwRcT9xO7uI3E7hl1P3E7hCkW3AnqbuRO4UpBuAupyl3AVuAC6uQU/Uup9zIp9yymGka\n" +
                "bFMupr1MamWU+4tE2qZeNQxKoXjVFo9t3tFJOLw01hp+Z1++4fzcqraY9nLOYOXOL7djlVVLe1Fc\n" +
                "WmHNlh6ZND0bTqWr6dcVZVKVSjWc7hVY7oyillYXxX4HE8Q09Xq6pd38rqrdQrVJSnUXKbTfmvL8\n" +
                "kdkoXf7PWVT2VKrhP3asd0fwMsqjznoKYeW0+Xl435dXt9SrO2nbuXtqLi4+99uJ125tqlF5eJQf\n" +
                "SS6f+Dteu0KELad3GOytFrEo8s/E4q1cr/3acoTqvk6b5N/LzIzm7qvR4OSde+M8OBA5S70yUJNb\n" +
                "HSn/AEy6P4HGzhKnLbOLT9GYZY2e3ZjnMp4VAAJUAAAIAAAYAAAAAAYAAAGAAAJK6odd8rqb6Zw/\n" +
                "oIH3T3VIy9YRHPRX2dSljUJc+ufyOShM4mP+uXx/Q5CEjfj9Ofmm62KZdSMykWUzVy3FqUyyn3Mq\n" +
                "l3LqYJuLSplt5mU+5O/uG03BpUiVMz7yVME9GlTJ3mbf3LKY09GjeTvM+8neCejRvAz7wAujcpll\n" +
                "PuZlMspjZ3BpU2WUzLu5dSymCbg1qp3LqZjUy6n3BNxa1MspmVTLb+4IsPcsi3Io5dykp9+QCRxP\n" +
                "EU1/lrTfWax3OpRbjJSi2mujR2biSWbOkv8AqfodYOTlv+nvfCmuGO06RxlO3o/sesWVLU7J8mpr\n" +
                "FWK+7L/k5ipw1pvEVCVfhi7Vy0t0rCu1GvT+HqefF6VarQqxq0akqdSDzGcHhp9mZ7s9Oi8c9xtv\n" +
                "tKubKtOlUpVITg8SpzjiUfkced5sePqd/QhZcWWC1OiliN3DELmn8JfzfMbfcDUdVtJX/Ct/T1Wl\n" +
                "1dGOI3EO0qb5t90G5RLZ7dBAvVpVKFWVKrCVOpB4lCSw0/RooJYAAAAAAZgAAAAAAAAAAAdWeadF\n" +
                "/dx9RI6fO0pPHRtDnpNS3tu4yXZ/Q3RlgwSeatOXZGxPmbcf2y5J6PUi+8QmWUjRhYepEqQhSJ3A\n" +
                "nR6mTvE7idw9l1OUy28z7idwF1aN5KmZ93cncBdWneTvM25k7wLq07wM+8Bl1cgpIncK8yUDG4mq\n" +
                "XcspsSmWTGnqcpllLkILIE3FoUy28SiV0GzuJrnyKSngq+ouTYjmLh+IamYUI92zgTmNef72j/a/\n" +
                "zOHOTk85Pb+PNccAABDYFozlCSlCTjJdGnhoqABaU5Tm5Tk5SfNtvLZUCRBAB5AMAAAIAAAAAEkA\n" +
                "YAAAAapZtdvpPP0FF4/wZruhwqJfyPsbE+Zjl9mHwNa6muHtnn6MUiUyi6kmjIzIZKolgnSyZO4o\n" +
                "SMaX3E7kLJAtL5JyLzzLAlfcTuKAA0ZuAoAFp//Z";

//
//        String param = "username=maji&password=majiji";
        String editString = "{\"username\": \"maji\",\n" +
                "        \"password\": \"majiji\",\n" +
                "        \"uploadResponse\": {\n" +
                "            \"imageID\": 1,\n" +
                "            \"title\": \"haha\"\n," +
                "            \"description\": \"huhuhu\",\n" +
                "            \"adultOrNot\": 0,\n" +
                "            \"tags\": [\n" +
                "                \"man\",\n" +
                "                \"person\",\n" +
                "                \"dark\",\n" +
                "                \"looking\",\n" +
                "                \"standing\",\n" +
                "                \"front\",\n" +
                "                \"black\",\n" +
                "                \"monitor\",\n" +
                "                \"holding\",\n" +
                "                \"water\",\n" +
                "                \"red\",\n" +
                "                \"room\",\n" +
                "                \"flying\"\n" +
                "            ],\n" +
                "            \"weatherData\": {\n" +
                "                \"tempDay\": 30,\n" +
                "                \"tempNight\": 12,\n" +
                "                \"cityName\": \"鹤峰县\",\n" +
                "                \"conditionDay\": \"晴\",\n" +
                "                \"conditionNight\": \"晴\"\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        String param = "username=maji&password=majiji&latitude=30&longitude=110&base64Str=".concat(base64);
        String test = "username=maji&password=majiji&uploadResponse=wahaha";
        JSONObject jsonObject = JSONObject.fromObject(editString);
        EditResponse editResponse = (EditResponse) JSONObject.toBean(jsonObject, EditResponse.class);
        System.out.println(editResponse.getUploadResponse().getImageID());

        System.out.println(sendPostRequest("http://127.0.0.1:8080/edit", editString));

        // Base64String.base64ToImage(base64, "src/main/resources/testout/test.jpg");
    }
    public static String sendGetRequest(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPostRequest(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            conn.setRequestProperty("user-agent",
                    "User-Agent,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
