def get_cdr(line)
    cdr = 0
    for i in 0..line.size-2
        for j in i+1..line.size-1
            if line[j]-1 == line[i] or line[j]+1 == line[i]
                cdr += j - i
            end
        end
    end
    cdr
end

def take_input()
    first_line = gets.chomp.split(" ")
    num_lines = first_line[0].to_i
    line_length = first_line[1].to_i
    lines = []
    for i in 1..num_lines
        lines.push gets.chomp.split(" ")[0..line_length-1].map &:to_i
    end
    lines
end

take_input.each { |line| p get_cdr line }
